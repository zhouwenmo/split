package com.flow.experiment;


import com.flow.experiment.model.ExperBo;

import java.util.List;

/**
 * 默认分流处理的构造者
 *
 * @author zwm
 * @date 2019-10-23
 */
public class DefaultExperSplitHandlerBuilder {
    /**
     * 构造试验流处理责任链对象，试验集合中试验id唯一
     *
     * @param experBoList 试验对象集合，装配按照list顺序进行从后向前装配（即第一个队形最后处理）。需要按照开始时间降序传入（最后开始的放在最后一个处理）。
     * @return 默认
     */
    public static AbstractExperSplitHandler builder(List<ExperBo> experBoList) {
        if (experBoList == null || experBoList.size() == 0) {
            return null;
        }
        AbstractExperSplitHandler result = null;
        for (ExperBo experBo : experBoList) {
            AbstractExperSplitHandler experSplitHandler = new DefaultExperSplitHandler(experBo);
            experSplitHandler.setNextHandler(result);
            result = experSplitHandler;
        }
        return result;
    }
}
