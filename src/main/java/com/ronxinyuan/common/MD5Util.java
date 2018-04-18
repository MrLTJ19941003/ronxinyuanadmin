package com.ronxinyuan.common;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 13045 on 2018/4/18.
 */
public class MD5Util {
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5= MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
         //加密后的字符串
         String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
         return newstr;
    }
    public static void main(String[] args){
        try {
            System.out.println(MD5Util.EncoderByMd5("111111"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
