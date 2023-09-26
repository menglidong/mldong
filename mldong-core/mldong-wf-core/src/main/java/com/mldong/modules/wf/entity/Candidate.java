package com.mldong.modules.wf.entity;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

/**
 *
 * 候选人实体
 * @author mldong
 * @date 2023/6/26
 */
@Data
@Builder
public class Candidate {
    // 用户ID
    private String userId;
    // 用户名
    private String userName;
    // 扩展属性
    private Dict ext = Dict.create();

    /**
     * 重写equals，方便去重
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Candidate) {
            Candidate tmp = (Candidate) obj;
            if (ObjectUtil.equals(this.getUserId(),tmp.getUserId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 重写hashCode，方便去重
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
