package com.mldong.modules.sys.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import com.mldong.modules.sys.api.UserApi;
import com.mldong.modules.sys.service.UserService;
import com.mldong.modules.sys.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author mldong
 * @date 2023/10/7
 */
@Component
@RequiredArgsConstructor
public class UserProvider implements UserApi {
    private final UserService userService;
    @Override
    public Dict findById(Long id) {
        UserVO vo = userService.findById(id);
        if(vo == null) {
            return Dict.create();
        }
        return BeanUtil.toBean(vo,Dict.class);
    }
}
