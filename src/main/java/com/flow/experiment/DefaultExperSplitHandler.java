package com.flow.experiment;


import com.flow.experiment.model.ExperBo;
import com.flow.experiment.model.ExperimentMark;

/**
 * 试验分流具体类
 *
 * @author zwm
 * @date 2019-10-15
 */
public class DefaultExperSplitHandler extends AbstractExperSplitHandler {
    private ExperBo experBo;

    public DefaultExperSplitHandler() {
        super();
    }

    public DefaultExperSplitHandler(ExperBo experBo) {
        super();
        this.experBo = experBo;
    }

    public ExperBo getExperBo() {
        return experBo;
    }

    public void setExperBo(ExperBo experBo) {
        this.experBo = experBo;
    }


    @Override
    public boolean through(ExperimentMark request, ExperimentMark response, ISpitBo splitBo) {
        if (experBo == null) {
            //试验数据空，分流结束
            return true;
        }
        //添加参与标记
        response.getParticipationSet().add(experBo.getExperimentId());
        return splitBo.split(request, response, experBo);
    }
}
