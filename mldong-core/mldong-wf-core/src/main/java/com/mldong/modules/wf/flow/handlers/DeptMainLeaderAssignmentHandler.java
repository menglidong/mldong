package com.mldong.modules.wf.flow.handlers;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.mldong.modules.sys.api.DeptApi;
import com.mldong.modules.wf.engine.AssignmentHandler;
import com.mldong.modules.wf.engine.core.Execution;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.web.LoginUserHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取当前部门分管领导
 * @author mldong
 * @date 2023/9/28
 */
public class DeptMainLeaderAssignmentHandler implements AssignmentHandler {
    private static DeptApi deptApi = SpringUtil.getBean(DeptApi.class);
    @Override
    public List<String> assign(TaskModel model, Execution execution) {
        List<String> ids = new ArrayList<>();
        Long deptId = LoginUserHolder.me().getDeptId();
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
        return "当前用户所属部门分管领导";
    }

    @Override
    public int getOrder() {
        return 40;
    }
}
