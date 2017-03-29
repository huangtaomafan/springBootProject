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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * http链接请求测试类
 * @author liuzq
 * @version $Id: HttpTest.java, v 0.1 2016年5月6日 上午9:44:52 liuzq Exp $
 */
public class HttpTest {

    public static void main(String[] args) {
        //        Map<String, String> messageMap = new HashMap<String, String>();
        //        messageMap.put("app_id", "zyyy_android");
        //        messageMap.put("app_key", "ZW5sNWVWOWhibVJ5YjJsaw==");
        //        messageMap.put("user_type", "2");
        //        messageMap.put("api_name", "api.hzpt.citizenapp.login");
        //        messageMap.put("params",
        //            "{'id_card_type':'SFZ','login_name':'330102199112132717','password':'8B39A8192CD5A961'}");
        //        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        //        String message = gson.toJson(messageMap);
        //                String message = "idNo=231005199206202532&name=刘黎明&mobile=13600001111&userId=llm13600001111&aliPayId=2088111122221";
        //        String message = "idNo=331002199006266582&name=魏巍2&mobile=13600001111&national=中国&gender=m&address=浙江省杭州市西湖区蒋村花园2-201&certValidBeginDate=2016-10-22&certValidEndDate=2036-12-22";
                        String message = "idNo=330104193006231346&name=张阿英&mobile=13910582131&cardNo=A10041925&psnId=0505050505050505";
//        String message = "msg=&psnId=0505050505050505";
        String urlstr = "http://127.0.0.1:8080/certifyBySMK";
        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
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
