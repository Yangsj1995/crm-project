package com.shsxt.crm.util;

import javax.servlet.http.HttpServletRequest;

public class UserLoginUtil {
    /**
     * 从request中获取userId,并解密。
     * @param request
     * @return
     */
    public static  String realseUserId(HttpServletRequest request){
        String userId = CookieUtil.getCookieValue(request,"userId");
        if(userId==null)
            return null;
        return Base64Util.decode(userId);

    }
}
