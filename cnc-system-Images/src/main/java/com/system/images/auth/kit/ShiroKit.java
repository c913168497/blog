package com.system.images.auth.kit;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 作者： Administrator
 * 创建时间：2017-04-21.
 * 版本：1.0
 */
public class ShiroKit {
    public static String md5(String password,String salt) {
        String p = null;
        p = new Md5Hash(password, salt).toHex().toUpperCase();
        return p;
    }

    public static boolean isEmpty(Object obj) {

        if(obj instanceof String) {
            return "".equals(obj);
        }

        if(obj instanceof Integer) {
            return (Integer)obj==0;
        }
        return obj == null;
    }


    public static void main(String[] args) {
        //System.out.println(ShiroKit.md5("admin","adminadmin"));
    }
}
