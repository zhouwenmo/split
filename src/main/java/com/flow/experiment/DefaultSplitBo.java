package com.flow.experiment;


import com.flow.experiment.model.ExperBo;
import com.flow.experiment.model.Experiment;
import com.flow.experiment.model.ExperimentMark;
import org.apache.commons.lang3.RandomUtils;

/**
 * 默认分流算法,分层分流算法
 *
 * @author zwm
 * @date 2019-10-22
 */
public class DefaultSplitBo implements ISpitBo {
    private int limit;
    private int random;
    private boolean startRandom;


    /**
     * 初始化，上限1000，随机数1000，未开启随机
     */
    public DefaultSplitBo() {
        this.limit = 1000;
        this.random = 1000;
        this.startRandom = false;
    }

    /**
     * 构造默认随机算法
     *
     * @param totalFlow 总流量数
     */
    public DefaultSplitBo(int totalFlow) {
        this.limit = totalFlow;
        this.random = totalFlow;
        this.startRandom = false;
    }

    @Override
    public boolean split(ExperimentMark request, ExperimentMark response, ExperBo experBo) {
        if (this.startRandom) {
            return splitByRandom(response, experBo);
        } else {
            return splitByLimit(request, response, experBo);
        }
    }

    /**
     * 根据上线阈值进行分流
     *
     * @param request  携带的标记
     * @param response 响应的标记
     * @param experBo  试验的业务信息
     * @return 分流结束标记
     */
    private boolean splitByLimit(ExperimentMark request, ExperimentMark response, ExperBo experBo) {
        if (request != null && request.getParticipationSet().contains(experBo.getExperimentId())) {
            //携带参与标记
            if (request.getExperiment() != null && request.getExperiment().getExperimentId().equals(experBo.getExperimentId())) {
                //携带此试验的试验标记，放入试验，分流结束
                response.setExperiment(request.getExperiment());
                return true;
            }
            //降低上限阈值
            limit = limit - experBo.getFlow() * experBo.getNum();
        } else {
            if (limit < 0) {
                //限制小于0直接返回
                return true;
            }
            //未参与过，开启随机算法
            startRandom = true;
            //生成随机数并分流
            int randomCreate = RandomUtils.nextInt(0, limit);
            Experiment split = split(randomCreate, experBo);
            if (split != null) {
                response.setExperiment(split);
                return true;
            }
            //降低随机数
            this.random = randomCreate - experBo.getNum() * experBo.getFlow();
        }
        return false;
    }

    /**
     * 根据随机数分流
     *
     * @param response 响应标记
     * @param experBo  试验信息
     * @return 分流结束标记
     */
    private boolean splitByRandom(ExperimentMark response, ExperBo experBo) {
        //已经开始随机算法
        if (random < 0) {
            //随机数小于0 结束后续处理
            return true;
        }
        Experiment split = split(random, experBo);
        if (split != null) {
            response.setExperiment(split);
            return true;
        }
        //降低随机数
        random = random - experBo.getNum() * experBo.getFlow();
        return false;
    }

    private Experiment split(int random, ExperBo experBo) {
        int version = random / experBo.getFlow() + 1;
        if (version > experBo.getNum()) {
            return null;
        }
        return new Experiment(experBo, version);
    }
}
