package com.mldong.modules.wf.flow.handlers;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.modules.sys.api.DeptApi;
import com.mldong.modules.wf.api.WfUserApi;
import com.mldong.modules.wf.engine.AssignmentHandler;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.core.ServiceContext;
import com.mldong.modules.wf.engine.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取当前部门分管领导
 * @author mldong
 * @date 2023/9/28
 */
public class ApplicantDeptMainLeaderAssignmentHandler implements AssignmentHandler {
    private static DeptApi deptApi = SpringUtil.getBean(DeptApi.class);
    @Override
    public List<String> assign(TaskModel model, Execution execution) {
        List<String> ids = new ArrayList<>();
        String operator = execution.getProcessInstance().getOperator();
        WfUserApi wfUserApi = ServiceContext.find(WfUserApi.class);
        Long deptId = Convert.toLong(wfUserApi.getDeptId(operator));
        Dict dict = deptApi.findById(deptId);
        if(ObjectUtil.isNotEmpty(dict)) {
           Long mainLeaderId = dict.getLong("mainLeaderId");
           if(mainLeaderId!=null) {
               ids.add(mainLeaderId.toString());
           }
        }
        return ids;
    }

    @Override
    public String getMessage() {
        return "发起人所属部门分管领导";
    }

    @Override
    public int getOrder() {
        return 20;
    }
}
