/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * http链接请求测试类
 * @author liuzq
 * @version $Id: HttpTest.java, v 0.1 2016年5月6日 上午9:44:52 liuzq Exp $
 */
public class HttpTest {

    public static void main(String[] args) {
        Map<String, String> messageMap = new HashMap<String, String>();
        messageMap.put("idNo", "231005199206202532");
        messageMap.put("name", "刘黎明");
        messageMap.put("mobile", "13600001111");
        messageMap.put("userId", "llm13600001111");
        messageMap.put("aliPayId", "2088111122221");
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String message = gson.toJson(messageMap);
        String urlstr = "http://127.0.0.1:8080/aliPayCertifyRecord";
        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            // 设置接收数据的格式
            connection.setRequestProperty("Accept", "application/json");
            // 设置发送数据的格式
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            BufferedOutputStream conOut = new BufferedOutputStream(connection.getOutputStream());
            conOut.write(message.getBytes());
            conOut.flush();
            conOut.close();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (Entry<String, List<String>> entry : map.entrySet()) {
                System.out.println("key=" + entry.getKey());
                for (String string : entry.getValue()) {
                    System.out.println("------" + string);
                }
            }
            String result = changeInputStreamToString(connection.getInputStream(), "UTF-8");
            System.out.println("ret:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String changeInputStreamToString(InputStream is,
                                                    String charsetName) throws UnsupportedEncodingException,
                                                                        IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, charsetName));

        StringBuffer sb = new StringBuffer();
        String str = null;

        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();

        return sb.toString();
    }

}
