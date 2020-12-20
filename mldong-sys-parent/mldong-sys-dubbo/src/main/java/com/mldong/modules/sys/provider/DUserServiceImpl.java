package com.mldong.modules.sys.provider;

import com.mldong.modules.sys.entity.SysUser;
import com.mldong.modules.sys.mapi.DUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0")
public class DUserServiceImpl implements DUserService {

    @Override
    public SysUser get(Long userId) {
        SysUser u = new SysUser();
        u.setUserName("test");
        return u;
    }
}
