package com.mldong.modules.sys.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Dict;
import com.mldong.base.MapToCamelCaseRowHandlerImpl;
import com.mldong.modules.sys.api.DeptApi;
import com.mldong.modules.sys.service.DeptService;
import com.mldong.modules.sys.vo.DeptVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 部门接口提供者
 * @author mldong
 * @date 2023/9/28
 */
@Component
@RequiredArgsConstructor
public class DeptProvider implements DeptApi {
    private final DeptService deptService;
    @Override
    public Dict findById(Long id) {
        DeptVO deptVO = deptService.findById(id);
        if(deptVO!=null) {
            Dict dict = BeanUtil.toBean(deptVO,Dict.class);
            new MapToCamelCaseRowHandlerImpl().handle(dict);
            return dict;
        }
        return null;
    }
}
