package com.mldong.modules.wf.engine.cfg;


import com.mldong.modules.wf.engine.Context;
import com.mldong.modules.wf.engine.core.ServiceContext;
import com.mldong.modules.wf.engine.impl.SimpleContext;
import com.mldong.modules.wf.engine.parser.impl.*;


/**
 *
 * 流程引擎配置类
 * @author mldong
 * @date 2022/6/12
 */
public class Configuration {
    public Configuration() {
        this(new SimpleContext());
    }
    public Configuration(Context context) {
        ServiceContext.setContext(context);
        ServiceContext.put("decision", DecisionParser.class);
        ServiceContext.put("end", EndParser.class);
        ServiceContext.put("fork", ForkParser.class);
        ServiceContext.put("join", JoinParser.class);
        ServiceContext.put("start", StartParser.class);
        ServiceContext.put("task", TaskParser.class);
        ServiceContext.put("custom",CustomParser.class);
        ServiceContext.put("wfSubProcess", WfSubProcessParser.class);
    }

}
