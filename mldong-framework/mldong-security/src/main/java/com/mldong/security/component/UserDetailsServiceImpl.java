package com.mldong.security.component;

import com.mldong.common.base.SsoUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        System.err.println(s);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SsoUser user = new SsoUser(s, passwordEncoder.encode("mldong@321"), new ArrayList<>());
        user.setUserId(1L);
        return user;
    }
}
