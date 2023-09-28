package com.mldong.modules.wf.flow.handlers;

import cn.hutool.core.collection.CollectionUtil;
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
 * 获取当前用户部门领导
 * @author mldong
 * @date 2023/9/28
 */
public class DeptLeaderAssignmentHandler implements AssignmentHandler {
    private static DeptApi deptApi = SpringUtil.getBean(DeptApi.class);
    @Override
    public List<String> assign(TaskModel model, Execution execution) {
        List<String> ids = new ArrayList<>();
        Long deptId = LoginUserHolder.me().getDeptId();
        Dict dict = deptApi.findById(deptId);
        if(ObjectUtil.isNotEmpty(dict)) {
            String leaderIds = dict.getStr("leaderIds");
            if(ObjectUtil.isNotEmpty(leaderIds)) {
                ids.addAll(CollectionUtil.newArrayList(leaderIds.split(",")));
            }
        }
        return ids;
    }
}
