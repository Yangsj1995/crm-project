package com.shsxt.crm.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    /**
     * 用md5算法 对密码进行加密
     * @param pwd
     * @return
     */
    public  static  String encode(String pwd){
        try {
            MessageDigest messageDigest =  MessageDigest.getInstance("md5");
            return Base64.encodeBase64String(messageDigest.digest(pwd.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static void main(String[] args) {
        System.out.println(encode("123"));
    }
}
