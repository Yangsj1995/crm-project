package com.shsxt.crm.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
    /**
     * ID加密，逻辑：
     * 1.对id进行转换
     * 2.用系统时间生成一个字符串。
     * 3.encode = 系统时间的字符串 + 系统时间的字符串(4,8)位  + 转换后的id +  (迷惑敌人，隐藏ID)
     * 4.将encode进行逆转。
     * 5.将encode里的'='转换成‘#’
     * @param id
     * @return
     */
    public static String encode(String id) {

        String encodeString = Base64.encodeBase64String(id.getBytes());
        String timeString = Base64.encodeBase64String((System.currentTimeMillis()+"").getBytes());
        String encode = timeString  + timeString.substring(4,8) + encodeString;
        encode =  new StringBuilder(encode).reverse().toString();

        return  encode.replaceAll("=","#");
    }

    /**
     * 解密ID,逻辑：
     * 1.将encode里的'#'转换成‘=’ MTLZNDU2
     * 2.将encode进行逆转
     * 3.获取userId ,它处于 组合字符串中“==”所处的位置往后6个字符 开始 到 组合字符串的结尾 见encode方法。
     * @param encodeId
     * @return
     */

    public static String decode(String encodeId) {
        String encode = encodeId.replaceAll("#","=");

        encode =  new StringBuilder(encode).reverse().toString();
        /**
         * 原先encode = 系统时间转换后的字符串 + 字符串的4位子字符串 + userId
         *    样例        xxxxxxxxxxxx==   +   4位数(xxxx)     + userId
         * 由于系统时间转换后的字符串必以‘==’字符结尾，所以可以算出 userId的位置 = encode.indexOf("==")  + 6;
         */

        int userIdIndex = encode.indexOf("==")  + 6;

        String userIdCode = encode.substring(userIdIndex);

        byte[] idBytes =  Base64.decodeBase64(userIdCode.getBytes());

        return new String(idBytes);
    }

    public static void main(String[] args) {
        System.out.println(decode(encode("12")));
    }
}
