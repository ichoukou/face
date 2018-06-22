package com.jyou.utils;

import java.security.MessageDigest;

/**
 * Created with IntelliJ IDEA.
 * User: YanBangQing
 * Date: 2017/12/26
 * Time:  14:53
 * ProjectName:card_center
 */
public class Md5Tools {

    public  static String MD5(String pwd) {

        String des = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] result = md.digest(pwd.getBytes("UTF-8"));
            StringBuilder buf = new StringBuilder();
            for (int i=0;i<result.length;i++) {
                byte b = result[i];
                buf.append(String.format("%02X", b));
            }
            des = buf.toString().toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("md5 failure");
        }
        return des;
    }
}
