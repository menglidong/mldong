package com.mldong.modules.wf.flow.handlers;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import com.mldong.modules.sys.api.UserApi;
import com.mldong.modules.wf.engine.CandidateHandler;
import com.mldong.modules.wf.engine.model.TaskModel;
import com.mldong.modules.wf.entity.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 全局的候选人处理类
 * @author mldong
 * @date 2023/11/21
 */
@Component
@RequiredArgsConstructor
public class GlobalCandidateHandler implements CandidateHandler {
    private final UserApi userApi;
    @Override
    public List<Candidate> handle(TaskModel model) {
        List<Candidate> candidates = new java.util.ArrayList<>();
        // 基于用户id去获取
        String candidateUsers = model.getCandidateUsers();
        if(ObjectUtil.isNotEmpty(candidateUsers)) {
            String[] userIds = candidateUsers.split(",");
            for (String userId : userIds) {
                Dict dict = userApi.findById(Convert.toLong(userId));
                if(ObjectUtil.isNotEmpty(dict)) {
                    candidates.add(Candidate.builder().userId(userId).userName(dict.getStr("userName")).build());
                }
            }
        }
        // 基于角色标识去获取
        String candidateGroups = model.getCandidateGroups();
        if(ObjectUtil.isNotEmpty(candidateGroups)) {
            for (String group : candidateGroups.split(",")) {
                List<Dict> dicts = userApi.selectUserListByRoleCode(group);
                if(ObjectUtil.isNotEmpty(dicts)) {
                    for (Dict dict : dicts) {
                        candidates.add(Candidate.builder().userId(Convert.toStr(dict.get("id"))).userName(dict.getStr("userName")).build());
                    }
                }
            }
        }
        return candidates;
    }
}
