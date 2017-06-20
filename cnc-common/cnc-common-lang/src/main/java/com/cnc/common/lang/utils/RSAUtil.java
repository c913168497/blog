package com.cnc.common.lang.utils;


import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 作者： Administrator
 * 创建时间：2017-04-22.
 * 版本：1.0
 */
public class RSAUtil {
    public String encodeBase64(String str){
        return Base64.encodeToString(str.getBytes());
    }
    public String decodeBase64(String str){
        return Base64.decodeToString(str);
    }
    public String encodeHex(String str){
        return  Hex.encodeToString(str.getBytes());
    }
    public String decodeHex(String str){
        return  new String(Hex.decode(str.getBytes()));
    }

    /**
     * 不可逆
     * @param name
     * @param password
     * @return 加密后的密码：toUpperCase()将字符全部转为大写
     */
    public String decodeMd5(String name,String password){

        return  new Md5Hash(name, password).toString().toUpperCase();//还可以转换为 toBase64()/toHex()
    }

    /**
     * 通过调用SimpleHash时指定散列算法，其内部使用了Java的MessageDigest实现。
     * @param name
     * @param password
     * @return
     */
    public String decodeSimpleHash(String name,String password){
        return new SimpleHash("SHA-1", name, password).toString();
    }

}
