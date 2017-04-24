package com.springboot.learn.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RSA签名工具类
 * Created by 刘志强 on 2017/4/24.
 */
public class RSAUtil {

    /**
     * 日志
     */
    private static final Logger logger                    = LoggerFactory.getLogger(RSAUtil.class);

    private static final int    DEFAULT_BUFFER_SIZE       = 8192;

    private static final String SIGN_CHARSET              = "UTF-8";

    private static final String SIGN_TYPE_RSA             = "RSA";

    private static final String SIGN_SHA256RSA_ALGORITHMS = "SHA256WithRSA";

    private static final String PRIVATE_KEY               = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCMameYoHKx4WeCJtQmI0hzj"
                                                            + "/rB8hAcw4cCxQ17UQAIlSsUMVq3vwioIlkixhn3Ubwd77zysFAv37MrsOi5smwc9volMcTySt0U3W7+hoQNDP+Tlg19"
                                                            + "/z1vKsB2OxreDkZxFTh3X/UPVOmRJWTQFOiO0CAKDI19E"
                                                            + "+rEH3mAX4Gy7YtAHWoyiNJWhTHuttIyh97FqGOoJYGoivINiyR6CM1mFx+HR+Ihl3+ErttkMgs36AeGLrQfm"
                                                            + "+8AR70zzgZijjKzNbnWq3wGV/a7lJnRaSICyHj4AwV2hGkEM/swayZYhguTKVJjLZChIzifJoQVleneqT"
                                                            + "/Mj2TYdIH9e5bwXAVdAgMBAAECggEAJP9UVI7SKbRuhYidlX7udCSXucqCHFIYnM43AysQizJiq2tlr8ySB1sliVgRN20919V3P6rdNgAHQZfXejZc7jTVLD+02PKI1hqSSFZBLP8jyEtdOrmqAkSNeZmRNrkkLl+dBTcvZt7EvA3SRlF8BUUpa/xqu24oCY0RgpWnovE2r7rHo/Hc07CySxQlrWVLcOALY7aGVT/B+rG64jSKD/VewVfTJ5Cmub31VBMjDCGhETYj1D/LZ5ZsPPIKjIiJeJaMJhiCSwxX9KSwIGBolvKlKwU9IfZoPOhMqKYD7qqTHVFEWR/39YxTVnRXcVF+7vbYY2AZM7kHfEYFfrJSMQKBgQDh8FlQFm82N8vW2jhQi70OPx+uXx/z2ozn/6D9YJVaTyQ9nTutsOAATznOggZ4ztsNlUpdc7hieYav6iWZIL/1cnMR2Z6bE+BGqjdKKmZuu5TUD7JxuLW8LIoInJ6FIwX9oPuFg/PKud6qGewz8Dz57Kz0eRncCIR4VTJfqx+y8wKBgQCfGRG8ULyOVtvmBjnTSQ2xa4AqOrOwYVKsJTlaz3PcVy32/CfFrRZnKBq/4aEtfeoXA/BroN1T2CWCaluj5NYJhrwQHEq5KcF4517IQPJRrdy1P+zU3XsHZ4TNYcT4qsJioO6AhFA7WuZBgdo33g3rIpXkWPeDLm3xvW8VKXxabwKBgBmWXKcDQ2kCfKVCZWpJyQlaAL6Aq1+0eoyzmInZbFCj8djAe8e55kRitIs8knsQAiLRq3BV8KXGWYiGnedvnA2x/nIhESNC5v1ZvZU6pAsaV0qE1JKodnxvFtrO0e0GAGGu341JWky1LBBbizJ+nIk5UoUFSfZvSIFQNLo86QQdAoGBAIJpMjiClYy3dhjfEZc3UpoT3ELBS3lQzUt+V70wP3Blpa/GkXNfpfV40jRm5o87nx6ug+w1ODb5/gUg2W1GrouwfYdXKYkNySEpxm+vjPGW6ktH0wHJaodvO1R+x85talkeQWHnEz0UDZm0rwMMaaxL9UHwCzLkZAq7lMpr7xrRAoGBAOD52PTG/8ympqZjraN+jkUJozw3rXjmQ0lN1oFRzJnGnd5Ii7rsA7hG8gsiZdk4QkGBvraG0qa/uXVI0hsMrpEA2c5wurNPSBF2ozWQN4PfkGs72ExaXk63O7X3vC/yQaXQkgNMgusw344CASreoLGHI7w4Bx8AbFPt9Eg5hUrb";

    private static final String PUBLIC_KEY                = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjGpnmKByseFngibUJiNIc4"
                                                            + "/6wfIQHMOHAsUNe1EACJUrFDFat78IqCJZIsYZ91G8He+88rBQL9+zK7DoubJsHPb6JTHE8krdFN1u/oaEDQz"
                                                            + "/k5YNff89byrAdjsa3g5GcRU4d1/1D1TpkSVk0BTojtAgCgyNfRPqxB95gF"
                                                            + "+Bsu2LQB1qMojSVoUx7rbSMofexahjqCWBqIryDYskegjNZhcfh0fiIZd/hK7bZDILN"
                                                            + "+gHhi60H5vvAEe9M84GYo4yszW51qt8Blf2u5SZ0WkiAsh4+AMFdoRpBDP7MGsmWIYLkylSYy2QoSM4nyaEFZXp3qk"
                                                            + "/zI9k2HSB/XuW8FwFXQIDAQAB";

    /**
     * RSA2签名
     *
     * @param map 待签名数据和签名字段字符串
     * @return 签名
     * @throws Exception 异常上层处理
     */
    public static String rsaSign(Map<String, String> map) throws Exception {
        String content = getSignContent(map);
        String reqSeq = map.get("reqSeq");
        logger.info(">>待签名数据为:" + reqSeq + "," + content);
        PrivateKey priKey = getPrivateKeyFromPKCS8(
            new ByteArrayInputStream(PRIVATE_KEY.getBytes()));
        Signature signature = Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        signature.initSign(priKey);
        signature.update(content.getBytes(SIGN_CHARSET));
        String sign = new String(Base64.encodeBase64(signature.sign()));
        logger.info(">>签名为:" + reqSeq + "," + sign);
        return sign;
    }

    /**
     * RSA2验签
     *
     * @param map 待验签数据、签名信息和签名字段字符串
     * @return 验签结果 true/false
     * @throws Exception 异常上层处理
     */
    public static boolean rsaCheck(Map<String, String> map) throws Exception {
        String content = getSignContent(map);
        String reqSeq = map.get("reqSeq");
        String sign = map.get("sign");
        logger.info(">>待验证的签名为:" + reqSeq + "," + sign);
        logger.info(">>生成签名的参数为:" + reqSeq + "," + content);
        PublicKey pubKey = getPublicKeyFromX509(new ByteArrayInputStream(PUBLIC_KEY.getBytes()));
        Signature signature = Signature.getInstance(SIGN_SHA256RSA_ALGORITHMS);
        signature.initVerify(pubKey);
        signature.update(content.getBytes(SIGN_CHARSET));
        boolean signResult = signature.verify(Base64.decodeBase64(sign.getBytes()));
        logger.info(">>验签结果:" + reqSeq + "," + signResult);
        return signResult;
    }

    /**
     * 把参数按照ASCII码递增排序，如果遇到相同字符则按照第二个字符的键值ASCII码递增排序
     * 将排序后的参数与其对应值，组合成“参数=参数值”的格式，并且把这些参数用&字符连接起来
     *
     * @param sortedParams 待签名数据和签名字段字符串
     * @return 待签名字符串
     */
    private static String getSignContent(Map<String, String> sortedParams) {
        //appId,method,bizContent,生成签名所需的参数
        String[] sign_param = sortedParams.get("sign_param").split(",");
        List<String> keys = new ArrayList<>();
        Collections.addAll(keys, sign_param);
        Collections.sort(keys);
        StringBuilder content = new StringBuilder();
        int index = 0;
        for (String key : keys) {
            String value = sortedParams.get(key);
            if (StringUtil.isNotEmpty(key) && StringUtil.isNotEmpty(value)) {
                content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
                index++;
            }
        }
        return content.toString();
    }

    private static PrivateKey getPrivateKeyFromPKCS8(InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(SIGN_TYPE_RSA);
        byte[] encodedKey = readText(ins).getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    private static PublicKey getPublicKeyFromX509(InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(SIGN_TYPE_RSA);
        StringWriter writer = new StringWriter();
        io(new InputStreamReader(ins), writer, -1);
        byte[] encodedKey = writer.toString().getBytes();
        encodedKey = Base64.decodeBase64(encodedKey);
        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }

    private static String readText(InputStream ins) throws IOException {
        Reader reader = new InputStreamReader(ins);
        StringWriter writer = new StringWriter();
        io(reader, writer, -1);
        return writer.toString();
    }

    private static void io(Reader in, Writer out, int bufferSize) throws IOException {
        if (bufferSize == -1) {
            bufferSize = DEFAULT_BUFFER_SIZE >> 1;
        }
        char[] buffer = new char[bufferSize];
        int amount;
        while ((amount = in.read(buffer)) >= 0) {
            out.write(buffer, 0, amount);
        }
    }
}
