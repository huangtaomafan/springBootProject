/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.springboot.learn.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springboot.learn.SpringBootLearnApplication;
import com.springboot.learn.util.RSAUtil;

import net.sf.json.JSONObject;

/**
 * RestTemplate 测试类
 * @author liuzq
 * @version $Id: RestTemplateTest.java, v 0.1 2017年3月31日 下午2:32:32 liuzq Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootLearnApplication.class)
@WebAppConfiguration //web层要加
public class RestTemplateSignTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRestPost() throws Exception {
        //post表单
        //        HttpHeaders headers = new HttpHeaders();
        //        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        //        map.add("title", title);
        //        map.add("desc", desc);
        //        map.add("userid", toUserId);
        //        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        //        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        //        System.out.println(response);

        //post json
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        //        LinkedMultiValueMap<String, String> messageMap = new LinkedMultiValueMap();
        //        messageMap.add("psnId", "141585773230231");
        //        messageMap.add("cardNo", "6222021202038149400");
        //        messageMap.add("idNo", "340323199009180459");
        //        messageMap.add("name", "王浩");
        //        messageMap.add("mobile", "15267182670");
        //        messageMap.add("reqSeq", "20170412142715000002863866");
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String reqSeq = System.currentTimeMillis() + "";
        //请求接口实际数据
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("psnId", "141585773230231");
        messageMap.put("cardNo", "6222021202038149400");
        messageMap.put("idNo", "340323199009180459");
        messageMap.put("name", "王浩");
        messageMap.put("mobile", "15267182670");
        messageMap.put("reqSeq", reqSeq);
        messageMap.put("national", "中国");
        messageMap.put("gender", "M");
        messageMap.put("address", "中国杭州近江时代大厦");
        messageMap.put("certValidEndDate", "2027-04-23");
        String messageMapStr = gson.toJson(messageMap);
        //请求网关数据
        Map<String, String> openMap = new HashMap<>();
        openMap.put("reqSeq", reqSeq);
        openMap.put("appId", "10001");
        openMap.put("method", "smkCounterRecord");
        openMap.put("bizContent", messageMapStr);
        openMap.put("sign_param", "appId,method,bizContent");
        //加签
        openMap.put("sign", RSAUtil.rsaSign(openMap));
        String message = gson.toJson(openMap);
        //        HttpEntity<LinkedMultiValueMap<String, String>> entity = new HttpEntity<LinkedMultiValueMap<String, String>>(
        //            messageMap, headers);
        HttpEntity<String> entity = new HttpEntity<String>(message, headers);
        String urlstr = "http://127.0.0.1:8088/gateway";
        ResponseEntity<String> resp = restTemplate.postForEntity(urlstr, entity, String.class);
        System.out.println(resp);
        String result = resp.getBody();
        JSONObject json = JSONObject.fromObject(result);
        //验签
        Map<String, String> checkMap = new HashMap<>();
        checkMap.put("reqSeq", reqSeq);
        checkMap.put("sign_param", "success,value");
        checkMap.put("success", json.getString("success"));
        checkMap.put("value", json.getString("value"));
        checkMap.put("sign", json.getString("sign"));
        System.out.println(RSAUtil.rsaCheck(checkMap));
        //url post
        //        String template = baseUrl + "/demo?app={0}&userId={1}";
        //        String url = MessageFormat.format(template,app,userId);
        //        ResponseEntity<String> resp2 = restTemplate.postForEntity(url,null,String.class);
        //        System.out.println(resp2);

        //请求图片
        //        HttpHeaders headers = new HttpHeaders();
        //        headers.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        //        HttpEntity<String> entity = new HttpEntity<String>(headers);
        //        ResponseEntity<byte[]> response = restTemplate.exchange(url,HttpMethod.GET, entity, byte[].class);
        //        byte[] imageBytes = response.getBody();
    }

}
