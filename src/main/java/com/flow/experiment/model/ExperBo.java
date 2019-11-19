package com.flow.experiment.model;

/**
 * 试验业务对象
 * @author zwm
 * @date 2019-10-15
 */
public class ExperBo {
    /**
     * 试验Id
     */
    private Integer experimentId;
    /**
     * 试验 层级id
     */
    private Integer layerId;
    /**
     * 类型 同一层及下的多种类型
     */
    private Integer type;
    /**
     * 版本数量
     */
    private Integer num;
    /**
     * 每个试验版本的流量
     */
    private Integer flow;

    public ExperBo() {
    }

    public ExperBo(Integer experimentId, Integer layerId, Integer type, Integer num, Integer flow) {
        this.experimentId = experimentId;
        this.layerId = layerId;
        this.type = type;
        this.num = num;
        this.flow = flow;
    }

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
    }

    public Integer getLayerId() {
        return layerId;
    }

    public void setLayerId(Integer layerId) {
        this.layerId = layerId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }
}
