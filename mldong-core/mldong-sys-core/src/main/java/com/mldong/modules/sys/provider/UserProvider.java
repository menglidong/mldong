package com.mldong.modules.sys.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mldong.base.CommonPage;
import com.mldong.base.LabelValueVO;
import com.mldong.base.MapToCamelCaseRowHandlerImpl;
import com.mldong.modules.sys.api.UserApi;
import com.mldong.modules.sys.dto.UserPageParam;
import com.mldong.modules.sys.entity.User;
import com.mldong.modules.sys.enums.AdminTypeEnum;
import com.mldong.modules.sys.mapper.UserMapper;
import com.mldong.modules.sys.service.UserService;
import com.mldong.modules.sys.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author mldong
 * @date 2023/10/7
 */
@Component
@RequiredArgsConstructor
public class UserProvider implements UserApi {
    private final UserService userService;
    private final UserMapper userMapper;
    @Override
    public Dict findById(Long id) {
        UserVO vo = userService.findById(id);
        if(vo == null) {
            return Dict.create();
        }
        return BeanUtil.toBean(vo,Dict.class);
    }
    @Override
    public CommonPage<Map<String,Object>> page(Page<Map<String,Object>> page, Dict query) {
        UserPageParam param = BeanUtil.toBean(query,UserPageParam.class);
        QueryWrapper queryWrapper = param.buildQueryWrapper(query);
        page = userMapper.selectMapsPage(page,queryWrapper);
        return CommonPage.toPage(page, new MapToCamelCaseRowHandlerImpl());
    }

    @Override
    public List<LabelValueVO> select(Dict dict) {
        return userService.select(BeanUtil.toBean(dict,UserPageParam.class));
    }

    @Override
    public CommonPage<Map<String, Object>> listByUserIdsAndKeywords(Integer pageNum,Integer pageSize, List<Long> userIds, String keywords) {
        return listByUserIdsAndKeywords(pageNum, pageSize,userIds,keywords, false);
    }

    @Override
    public CommonPage<Map<String, Object>> listByUserIdsAndKeywords(Integer pageNum, Integer pageSize, List<Long> userIds, String keywords, boolean orderByFieldId) {
        IPage<Map<String,Object>> page = new Page<>();
        page.setCurrent(Long.valueOf(pageNum));
        page.setSize(Long.valueOf(pageSize));
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery(User.class);
        if(CollectionUtil.isNotEmpty(userIds)) {
            lambdaQueryWrapper.in(User::getId,userIds);
            if(orderByFieldId) {
                String userIdsStr = CollectionUtil.join(userIds, ",");
                // order by field指定id排序
                lambdaQueryWrapper.last(StrUtil.format("order by field(id,{})", userIdsStr));
            }
        }
        lambdaQueryWrapper.ne(User::getAdminType, AdminTypeEnum.SUPER_ADMIN);
        handleKeywords(lambdaQueryWrapper,keywords);
        page = userMapper.selectMapsPage(page, lambdaQueryWrapper);
        return CommonPage.toPage(page, new MapToCamelCaseRowHandlerImpl());
    }

    @Override
    public CommonPage<Map<String, Object>> listExcludeUserIdsByKeywords(Integer pageNum, Integer pageSize, List<Long> userIds, String keywords) {
        IPage<Map<String,Object>> page = new Page<>();
        page.setCurrent(Long.valueOf(pageNum));
        page.setSize(Long.valueOf(pageSize));
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery(User.class);
        if(CollectionUtil.isNotEmpty(userIds)) {
            lambdaQueryWrapper.notIn(User::getId,userIds);
        }
        lambdaQueryWrapper.ne(User::getAdminType, AdminTypeEnum.SUPER_ADMIN);
        handleKeywords(lambdaQueryWrapper,keywords);
        page = userMapper.selectMapsPage(page, lambdaQueryWrapper);
        return CommonPage.toPage(page, new MapToCamelCaseRowHandlerImpl());
    }

    @Override
    public List<Dict> selectUserListByRoleCode(String roleCode) {
        List<User> userList = userMapper.selectUserListByRoleCode(roleCode);
        return BeanUtil.copyToList(userList,Dict.class);
    }

    /**
     * 处理搜索关键字
     * @param lambdaQueryWrapper
     * @param keywords
     */
    private void handleKeywords(LambdaQueryWrapper<User> lambdaQueryWrapper, String keywords) {
        if (StrUtil.isNotEmpty(keywords)) {
            lambdaQueryWrapper.and(new Consumer<LambdaQueryWrapper<User>>() {
                @Override
                public void accept(LambdaQueryWrapper<User> userLambdaQueryWrapper) {
                    userLambdaQueryWrapper.or().like(User::getUserName,keywords);
                    userLambdaQueryWrapper.or().like(User::getRealName, keywords);
                    userLambdaQueryWrapper.or().like(User::getMobilePhone, keywords);
                }
            });
        }
    }
}
