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

        if (through(request, response, splitBo) || nextHandler == null) {
            return;
        }
        nextHandler.handler(request, response, splitBo);
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
}
