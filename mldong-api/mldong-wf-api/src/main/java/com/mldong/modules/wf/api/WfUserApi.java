package com.mldong.modules.wf.api;

/**
 * 工作流用户信息api
 * @author mldong
 * @date 2023/10/7
 */
public interface WfUserApi {
    /**
     * 获取用户名
     * @param userId
     * @return
     */
    String getRealName(String userId);
    /**
     * 获取部门id
     * @param userId
     * @return
     */
    String getDeptId(String userId);
    /**
     * 获取部门名称
     * @param userId
     * @return
     */
    String getDeptName(String userId);
    /**
     * 获取岗位id
     * @param userId
     * @return
     */
    String getPostId(String userId);
    /**
     * 获取岗位名称
     * @param userId
     * @return
     */
    String getPostName(String userId);
}
