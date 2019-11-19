import com.flow.experiment.model.ExperBo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019-11-18.
 */
public class DemoExperList {
    public static List<ExperBo> getDemoExper1() {
        List<ExperBo> demoExperList = new ArrayList<>();
        //层级1，类型2，版本数量4个，每个版本为23（针对千分制）的流量,第二个开始的试验
        demoExperList.add(new ExperBo(2, 1, 2, 4, 23));
        //层级1，类型1，版本数量3个，每个版本为25（针对千分制）的流量，第一个开始的试验
        demoExperList.add(new ExperBo(1, 1, 1, 3, 25));
        return demoExperList;
    }
    public static List<ExperBo> getDemoExper2() {
        List<ExperBo> demoExperList = new ArrayList<>();
        //层级2，类型2，版本数量2个，每个版本为5（针对千分制）的流量，第三个开始的试验
        demoExperList.add(new ExperBo(5, 2, 2, 4, 5));
        //层级2，类型1，版本数量2个，每个版本为27（针对千分制）的流量，第二个开始的试验
        demoExperList.add(new ExperBo(4, 2, 1, 4, 27));
        //层级2，类型1，版本数量5个，每个版本为10（针对千分制）的流量，第一个开始的试验
        demoExperList.add(new ExperBo(3, 2, 1, 5, 10));
        return demoExperList;
    }


}
