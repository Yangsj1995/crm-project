package com.shsxt.crm.proxy;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shsxt.crm.SystemLog;
import com.shsxt.crm.util.CookieUtil;
import com.shsxt.crm.util.JsonUtil;
import com.shsxt.crm.util.UserLoginUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;


import com.shsxt.crm.service.UserService;

import com.shsxt.crm.vo.Log;
import com.shsxt.crm.vo.User;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author lp
 * 日志收集代理实现
 */
@Component
@Aspect
public class SystemLogProxy {

//	@Autowired
//    private SystemLogDao systemLogDao;

    @Autowired
    private HttpServletRequest request;

//    @Autowired
//    private UserService userService;

    /**
     * 1.收集操作的模块信息
     * 操作的是哪个模块
     * 操作时间  ip 结果。。。
     * 2.定位到模块
     * com.shsxt.crm.controller.*.*(..)
     *
     * @annotation(com.shsxt.crm.annotations.SystemLog) 3.通知类型
     * 定义环绕通知
     * 4.日志数据添加
     * 方法执行完毕  添加日志数据到数据库中
     */

    @Pointcut("@annotation(com.shsxt.crm.SystemLog)")
    public void cut() {
    }


    @Around("cut()&&@annotation(systemLog)")
    public Object aroundMethod(ProceedingJoinPoint pjp, SystemLog systemLog) throws Throwable {

        Log log = new Log();

        log.setCreateDate(new Date());

        log.setDescription(systemLog.desc());

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        //获得调用的方法
        log.setMethod(signature.getMethod().toString());
        //获得请求人的IP
        log.setRequestIp(request.getRemoteAddr());
        //获取客户端请求的参数
        Map<String, String[]> map = request.getParameterMap();

        log.setParams(JSON.toJSONString(map));

        long start = System.currentTimeMillis();

        Object result = pjp.proceed();// 执行目标对象方法

        long end = System.currentTimeMillis();

        log.setType("0");
        //目标方法执行了多少毫秒。
        log.setExecuteTime(Integer.parseInt((end - start) + ""));
        //获取请求的用户真实姓名
        String trueName =  CookieUtil.getCookieValue(request,"trueName");

        log.setCreateMan(trueName);

        //设置目标对象的执行结果.
        log.setResult(JSON.toJSONString(result));

        System.out.println(JsonUtil.toJson(log));
//		systemLogDao.insert(log);
        return result;
    }


}
