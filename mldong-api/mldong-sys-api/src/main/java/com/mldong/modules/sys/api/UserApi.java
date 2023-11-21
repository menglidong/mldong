package com.mldong.modules.sys.api;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mldong.base.CommonPage;
import com.mldong.base.LabelValueVO;

import java.util.List;
import java.util.Map;

/**
 * 系统用户api
 * @author mldong
 * @date 2023/10/7
 */
public interface UserApi {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Dict findById(Long id);
    /**
     * 分页查询用户信息
     * @param page
     * @param query
     * @return
     */
    CommonPage<Map<String,Object>> page(Page<Map<String,Object>> page, Dict query);

    /**
     * 下拉选择用户
     * @param dict
     * @return
     */
    List<LabelValueVO> select(Dict dict);

    /**
     * 指定用户id中关键字搜索
     * @param pageNum
     * @param pageSize
     * @param userIds
     * @param keywords
     * @return
     */
    CommonPage<Map<String,Object>> listByUserIdsAndKeywords(Integer pageNum,Integer pageSize, List<Long> userIds,String keywords);
    /**
     * 指定用户id中关键字搜索
     * @param pageNum
     * @param pageSize
     * @param userIds
     * @param keywords
     * @param orderByFieldId 是否按照用户id排序
     * @return
     */
    CommonPage<Map<String,Object>> listByUserIdsAndKeywords(Integer pageNum,Integer pageSize, List<Long> userIds,String keywords,boolean orderByFieldId);
    /**
     * 关键字搜索-排除指定id
     * @param pageNum
     * @param pageSize
     * @param userIds
     * @param keywords
     * @return
     */
    CommonPage<Map<String,Object>> listExcludeUserIdsByKeywords(Integer pageNum, Integer pageSize, List<Long> userIds, String keywords);

    /**
     * 根据角色标识获取用户列表
     * @param roleCode
     * @return
     */
    List<Dict> selectUserListByRoleCode(String roleCode);

}
