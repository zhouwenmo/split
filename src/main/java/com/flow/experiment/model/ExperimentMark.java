package com.flow.experiment.model;

import java.util.Set;

/**
 * 试验标记对象
 *
 * @author zwm
 * @date 2019-10-17
 */
public class ExperimentMark {
    /**
     * 参与标记集合，代表参与了试验，最少一个
     */
    private Set<Integer> participationSet;
    /**
     * 分配的试验，可为空
     */
    private Experiment experiment;

    public Set<Integer> getParticipationSet() {
        return participationSet;
    }

    public void setParticipationSet(Set<Integer> participationSet) {
        this.participationSet = participationSet;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    @Override
    public String toString() {
        return "ExperimentMark{" +
                "participationSet=" + participationSet +
                ", experiment=" + experiment +
                '}';
    }
}
