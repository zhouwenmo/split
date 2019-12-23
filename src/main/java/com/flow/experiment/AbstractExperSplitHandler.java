package com.flow.experiment;


import com.flow.experiment.model.ExperimentMark;

import java.util.HashSet;

/**
 * 试验分流处理者抽象类（责任链）
 * 注意责任链是有顺序的，新增的试验，要放在责任链最后
 *
 * @author zwm
 * @date 2019-10-15
 */
public abstract class AbstractExperSplitHandler {
    /**
     * 下一个处理者
     */
    private AbstractExperSplitHandler nextHandler;

    /**
     * 处理试验
     *
     * @param request  携带参数
     * @param response 此参数不能为空，这个参数需要返回用户使用
     * @param splitBo  分流业务类，不可为空
     */
    public final void handler(ExperimentMark request, ExperimentMark response, ISpitBo splitBo) {
        if (response == null || splitBo == null) {
            //不进行分流处理
            return;
        }
        if (response.getParticipationSet() == null) {
            response.setParticipationSet(new HashSet<>());
        }
        //流过当前管道
        boolean through = through(request, response, splitBo);
        if (through) {
            //成功命中版本，进入上层通道处理
            handler(response);
            return;
        }
        if (nextHandler == null) {
            //到达最后一个处理者返回
            return;
        }
        //下一个管道处理者处理
        nextHandler.handler(request, response, splitBo);
    }

    /**
     * 上层管道处理，只加入响应标记
     *
     * @param response 响应内容
     */
    private void handler(ExperimentMark response) {
        //流过上层通道
        through(response);
        if (nextHandler != null) {
            //存在下级流过下级通道
            nextHandler.handler(response);
        }
    }

    public AbstractExperSplitHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(AbstractExperSplitHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 流过试验，处理
     *
     * @param request  携带请求参数
     * @param response 携带响应参数
     * @param splitBo  分流处理类
     * @return 是否分流结束
     */
    protected abstract boolean through(ExperimentMark request, ExperimentMark response, ISpitBo splitBo);

    /**
     * 上层管道流过，只加入参与标记
     *
     * @param response 响应内容
     */
    protected abstract void through(ExperimentMark response);
}
