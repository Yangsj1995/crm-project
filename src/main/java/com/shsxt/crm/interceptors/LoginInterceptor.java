package com.shsxt.crm.interceptors;

import com.shsxt.crm.service.UserService;
import com.shsxt.crm.util.AssertUtil;
import com.shsxt.crm.util.CookieUtil;
import com.shsxt.crm.util.UserLoginUtil;
import com.shsxt.crm.vo.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor  extends HandlerInterceptorAdapter {

    @Resource
    public  UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String userId = UserLoginUtil.realseUserId(request);

        AssertUtil.isTrue(userId==null,"用户未登录");

        User user = userService.queryUserById(userId);

        AssertUtil.isTrue(user ==null,"用户不存在");

        return true;
    }
}
