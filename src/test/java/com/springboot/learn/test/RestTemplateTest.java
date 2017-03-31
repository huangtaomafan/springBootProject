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

/**
 * RestTemplate 测试类
 * @author liuzq
 * @version $Id: RestTemplateTest.java, v 0.1 2017年3月31日 下午2:32:32 liuzq Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootLearnApplication.class)
@WebAppConfiguration //web层要加
public class RestTemplateTest {

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
        Map<String, String> messageMap = new HashMap<String, String>();
        messageMap.put("idNo", "231005199206202532");
        messageMap.put("name", "刘黎明");
        messageMap.put("mobile", "13600001111");
        messageMap.put("userId", "llm13600001111");
        messageMap.put("aliPayId", "2088111122221");
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String message = gson.toJson(messageMap);
        HttpEntity<String> entity = new HttpEntity<String>(message, headers);
        String urlstr = "http://192.168.23.239:8080/aliPayCertifyRecord";
        ResponseEntity<String> resp = restTemplate.postForEntity(urlstr, entity, String.class);
        System.out.println(resp);

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
