package com.xlzhou.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Auther: zxl
 * @Date: 2020/9/9 - 09 - 09 - 23:23
 */
public class MD5Utils {

    public String encryption(String secretkey,String password){
        //加密算法
        String hashAlgorithmName = "MD5";
        //加密对象
        Object credentials = password;
        //盐值秘钥  对用户名进行加盐
        Object salt = ByteSource.Util.bytes(secretkey);
        //设置散列次数： 意为加密几次
        int hashIterations = 1024;
        //进行加密
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        //SimpleHash simpleHash=new SimpleHash("MD5", "密码", "盐值秘钥", 1024);
        System.out.println(result);
        //返回加密过后的对象
        return result.toString();
    }
}
