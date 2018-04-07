package com.shsxt.crm.controller;

import com.shsxt.crm.base.BaseController;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.util.CookieUtil;
import com.shsxt.crm.util.UserLoginUtil;
import com.shsxt.crm.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class IndexController extends BaseController {

    @Resource
    UserService userService ;

    @RequestMapping("index")
    public String  index(){
      // Model model + String 视图名字 == modelANDView
        return  "index";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request) throws UnsupportedEncodingException {
        /**
         * 从request中，获取cookie 得到userName,和trueName
         */

        request.setAttribute("userName", CookieUtil.getCookieValue(request,"userName"));
        request.setAttribute("trueName", CookieUtil.getCookieValue(request,"trueName"));
        return "main";
    }
}
