package com.shsxt.crm.util;

import com.shsxt.crm.base.exceptions.ParamsException;

public class AssertUtil {

    public static void isTrue(boolean flag,String msg){

        if(flag){
            throw  new ParamsException(300,msg);
        }
    }

    public static void isTrue(boolean flag,Integer code,String msg){

        if(flag){
            throw  new ParamsException(code,msg);
        }
    }

    public static void main(String[] args) {
        String str = null;
        str.split("==");
        System.out.println("hello");
    }

}
