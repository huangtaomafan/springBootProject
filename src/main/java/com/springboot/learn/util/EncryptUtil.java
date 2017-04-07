/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.util;

import java.io.UnsupportedEncodingException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 密码工具类
 * 字符串 DESede(3DES) 加密
 * ECB模式/使用PKCS7方式填充不足位,目前给的密钥是192位
 * 3DES（即Triple DES）是DES向AES过渡的加密算法（1999年，NIST将3-DES指定为过渡的
 * 加密标准），是DES的一个更安全的变形。它以DES为基本模块，通过组合分组方法设计出分组加
 * 密算法，其具体实现如下：设Ek()和Dk()代表DES算法的加密和解密过程，K代表DES算法使用的
 * 密钥，P代表明文，C代表密表，这样，
 * 3DES加密过程为：C=Ek3(Dk2(Ek1(P)))
 * 3DES解密过程为：P=Dk1((EK2(Dk3(C)))
 * @author liuzq
 * @version $Id: EncryptUtil.java, v 0.1 2016年11月24日 下午7:17:29 liuzq Exp $
 */
public class EncryptUtil {

    /** 日志 */
    private static final Logger logger    = LoggerFactory.getLogger(EncryptUtil.class);

    /**
     * @param args在java中调用sun公司提供的3DES加密解密算法时，需要使
     * 用到$JAVA_HOME/jre/lib/目录下如下的4个jar包：
     *jce.jar
     *security/US_export_policy.jar
     *security/local_policy.jar
     *ext/sunjce_provider.jar 
     */

    private static final String Algorithm = "DESede";                                  //定义加密算法,可用 DES,DESede,Blowfish

    /**
     * 
     * @param keybyte 加密密钥
     * @param src 待加密数据
     * @return
     */
    private static byte[] encryptMode(byte[] keybyte, byte[] src) {
        if (keybyte == null || src == null) {
            return null;
        }

        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (Exception e) {
            logger.error("encryptMode异常:", e);
        }
        return null;
    }

    /**
     * 
     * @param keybyte 加密密钥
     * @param src 待解密数据
     * @return
     */
    private static byte[] decryptMode(byte[] keybyte, byte[] src) {
        if (keybyte == null || src == null) {
            return null;
        }

        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (Exception e) {
            logger.error("decryptMode异常:", e);
        }
        return null;
    }

    private static byte[] hex(String username) {
        String key = "smkmus";//关键字
        String f = DigestUtils.md5Hex(username + key);
        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i = 0; i < 24; i++) {
            enk[i] = bkeys[i];
        }
        return enk;
    }

    /**
     * 
     * @param srcData 待加密串
     * @return
     */
    public static String encrypt(final String srcData) {
        if (StringUtils.isBlank(srcData)) {
            return null;
        }

        byte[] key = hex("x38Uhyr9");//加密key
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        byte[] encoded = null;
        String encodedHex = null;

        try {
            encoded = encryptMode(key, srcData.getBytes("GBK"));
            if (encoded == null) {
                return null;
            }
            encodedHex = StringUtil.byte2HexStr(encoded, encoded.length);
        } catch (UnsupportedEncodingException e) {
            logger.error("encrypt异常:", e);
        }
        return encodedHex;
    }

    /**
     * 
     * @param srcData 待解密串
     * @return
     */
    public static String decrypt(final String srcData) {
        if (StringUtils.isBlank(srcData)) {
            return null;
        }
        byte[] key = hex("x38Uhyr9");//加密key
        Security.addProvider(new com.sun.crypto.provider.SunJCE());

        byte[] decodedBytes = decryptMode(key, StringUtil.hexStr2Bytes(srcData));
        if (decodedBytes == null) {
            return null;
        }
        String decodedStr = new String(decodedBytes);
        return decodedStr;
    }

    /**
     * 密码加密处理
     * @param str
     * @return
     */
    public static String encodePwd(String str) {
        if (StringUtil.isBlank(str)) {
            return null;
        }
        String encode = null;
        try {
            encode = SecurityUtil.hashWithBase64Encoded("SHA1", str.getBytes());
        } catch (Exception e) {
            encode = null;
            logger.error("encodePwd异常:", e);
        }
        encode = StringUtil.str2HexStr(encode);
        return encode;
    }
}
