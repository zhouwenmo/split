import com.flow.experiment.AbstractExperSplitHandler;
import com.flow.experiment.DefaultExperSplitHandlerBuilder;
import com.flow.experiment.DefaultSplitBo;
import com.flow.experiment.model.ExperBo;
import com.flow.experiment.model.Experiment;
import com.flow.experiment.model.ExperimentMark;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2019-11-18.
 */
public class TestRunner {

    @Test
    public void testEmptySplit() {
        //未参与试验
        ExperimentMark request = null;
        //获取试验列表
        List<ExperBo> demoExper = DemoExperList.getDemoExper1();
        AbstractExperSplitHandler builder = DefaultExperSplitHandlerBuilder.builder(demoExper);
        ExperimentMark response = new ExperimentMark();
        builder.handler(request, response, new DefaultSplitBo());
        System.out.println(response);
    }

    @Test
    public void testAllThroughNullSplit() {
        //参与 3,4,5 试验，未分入对应流量
        ExperimentMark request = new ExperimentMark();
        Set<Integer> mark = new HashSet<>();
        mark.add(3);
        mark.add(4);
        mark.add(5);
        request.setParticipationSet(mark);
        //获取试验列表
        List<ExperBo> demoExper = DemoExperList.getDemoExper2();
        AbstractExperSplitHandler builder = DefaultExperSplitHandlerBuilder.builder(demoExper);
        ExperimentMark response = new ExperimentMark();
        builder.handler(request, response, new DefaultSplitBo());
        System.out.println(response);
    }

    @Test
    public void testAlreadySplit() {
        //参与 3,4 试验，分到4试验
        ExperimentMark request = new ExperimentMark();
        Set<Integer> mark = new HashSet<>();
        mark.add(3);
        mark.add(4);
        request.setParticipationSet(mark);
        request.setExperiment(new Experiment(4, 4, 1, 2));
        //获取试验列表
        List<ExperBo> demoExper = DemoExperList.getDemoExper2();
        AbstractExperSplitHandler builder = DefaultExperSplitHandlerBuilder.builder(demoExper);
        ExperimentMark response = new ExperimentMark();
        builder.handler(request, response, new DefaultSplitBo());
        System.out.println(response);
    }
}
