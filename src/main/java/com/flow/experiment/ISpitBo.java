package com.flow.experiment;


import com.flow.experiment.model.ExperBo;
import com.flow.experiment.model.ExperimentMark;

/**
 * 分流对象接口,继承实现自定义接口
 *
 * @author zwm
 * @date 2019-10-23
 */
public interface ISpitBo {
    /**
     * 进行分流
     *
     * @param request  携带的标记
     * @param response 响应的标记
     * @param experBo  试验的业务信息
     * @return 分流结束标记
     */
    public boolean split(ExperimentMark request, ExperimentMark response, ExperBo experBo);
}
