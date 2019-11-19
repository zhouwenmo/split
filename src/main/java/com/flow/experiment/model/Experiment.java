package com.flow.experiment.model;


/**
 * 试验对象
 *
 * @author zwm
 * @date 2019-10-17
 */
public class Experiment {
    /**
     * 试验的id
     */
    private Integer experimentId;
    /**
     * 版本
     */
    private Integer version;
    /**
     * 试验类型 ,同一层及下的多种类型
     */
    private Integer type;
    /**
     * 层级id
     */
    private Integer layerId;

    public Experiment() {
    }

    public Experiment(Integer experimentId, Integer version, Integer type, Integer layerId) {
        this.experimentId = experimentId;
        this.version = version;
        this.type = type;
        this.layerId = layerId;
    }

    public Integer getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(Integer experimentId) {
        this.experimentId = experimentId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLayerId() {
        return layerId;
    }

    public void setLayerId(Integer layerId) {
        this.layerId = layerId;
    }

    public Experiment(ExperBo experBo, Integer version) {
        this.experimentId = experBo.getExperimentId();
        this.type = experBo.getType();
        this.layerId = experBo.getLayerId();
        this.version = version;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "experimentId=" + experimentId +
                ", version=" + version +
                ", type=" + type +
                ", layerId=" + layerId +
                '}';
    }
}
