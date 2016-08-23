/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.springboot.learn.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * JSON������
 * 
 * @author longlong.gu
 * @version $Id: JsonUtil.java, v 0.1 2012-9-6 ����09:42:40 longlong.gu Exp $
 */
public class JsonUtil {

    /** ��ͨ��־��¼�� */
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * JSON����ת����Map.���ڽ���http�������Ӧ����
     * 
     * @param jsonString
     *            �����ʽJSON����
     * @return
     */
    public static Map<String, String> convertJSON2LinkedHashMap(String jsonString) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        return toMap(jsonString, resultMap);
    }

    /**
     * JSON����ת����Map.���ڽ���http�������Ӧ����
     * 
     * @param jsonString
     *            �����ʽJSON����
     * @return
     */
    public static Map<String, String> convertJSON2Map(String jsonString) {

        Map<String, String> resultMap = new HashMap<String, String>();
        return toMap(jsonString, resultMap);

    }

    /**
     * ת����map
     * 
     * @param jsonString
     * @param resultMap
     * @return
     */
    @SuppressWarnings("rawtypes")
    private static Map<String, String> toMap(String jsonString, Map<String, String> resultMap) {
        if (StringUtil.isBlank(jsonString)) {
            return resultMap;
        }
        if (resultMap == null) {
            resultMap = new HashMap<String, String>();
        }
        // ת��ΪJSON�����޷�ת���򷵻�""
        JSONObject json = null;
        try {
            json = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            logger.warn("�����JSON�ַ����Ƿ���" + jsonString);
            return resultMap;
        }

        if (json == null || json.isEmpty()) {
            logger.warn("�����JSON�ַ������κ����ݣ�" + jsonString);
            return resultMap;
        }

        String key = "";
        String value = "";
        for (Iterator keyIter = json.keys(); keyIter.hasNext();) {
            key = String.valueOf(keyIter.next());
            value = String.valueOf(json.get(key));
            resultMap.put(key, value);
        }

        return resultMap;
    }

    /**
     * JSON����ת����LIST
     * 
     * @param jsonString
     *            �����ʽJSON����
     * @param className
     *            ��·��+����
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static List<?> convertJSON2List(String jsonString, String className) {

        Object object = null;

        List<?> list = new ArrayList();

        if (StringUtil.isEmpty(jsonString)) {
            return list;
        }
        try {

            // ���JSON��Ϊ�յĻ����������쳣���᷵��һ��JSONNull�������JSON�����С�
            JSONArray jarr = JSONArray.fromObject(jsonString);

            object = Class.forName(className).newInstance();

            list = (List) JSONArray.toCollection(jarr, object.getClass());

        } catch (Exception e) {
            logger.error("JSON����ת���쳣", jsonString);
        }
        return list;
    }

    /**
     * �����ݶ���ת����json�ַ��� DTO�������磺{"id" : idValue, "name" : nameValue, ...}
     * ����������磺[{}, {}, {}, ...] map�������磺{key1 : {"id" : idValue, "name" :
     * nameValue, ...}, key2 : {}, ...}
     * 
     * @param object
     * @return
     */
    public static String getJSONString(Object object) throws Exception {
        String jsonString = null;
        JsonConfig jsonConfig = new JsonConfig();
        if (object != null) {
            if (object instanceof Collection || object instanceof Object[]) {
                jsonString = JSONArray.fromObject(object, jsonConfig).toString();
            } else {
                jsonString = JSONObject.fromObject(object, jsonConfig).toString();
            }
        }
        return jsonString == null ? "{}" : jsonString;
    }

    /**
     * ��array����ת����Json����
     * 
     * @author sibai.zhang
     * 
     * @param object 
     *          ����Object���� ��Ϊ�շ���null��
     * @param filterProperty
     *          �������ԣ����˵����ܲ����ĵݹ����ԡ���Ϊ�ղ���⣩
     * @return 
     *          Json��ʽ���ַ��������ת���쳣������null��
     */
    public static String arrayObject2Json(Object object, final List<String> filterProperty) {

        // �������Ϊ�շ���null;
        if (object == null) {
            return null;
        }
        try {
            if (!CollectionUtils.isEmpty(filterProperty)) {
                // Json����
                JsonConfig config = new JsonConfig();
                config.setJsonPropertyFilter(new PropertyFilter() {
                    /**
                     * ���ÿ��ܳ��ֵݹ������
                     * @see net.sf.json.util.PropertyFilter#apply(java.lang.Object, java.lang.String, java.lang.Object)
                     */
                    public boolean apply(Object source, String name, Object value) {
                        // ���ÿ��ܳ��ֵݹ������
                        if (filterProperty.contains(name)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                // ��pojo����ת����Json����
                JSONArray jsonArray = JSONArray.fromObject(object, config);
                // ���String֧����
                return jsonArray.toString();
            } else {
                // ��pojo����ת����Json����
                JSONArray jsonArray = JSONArray.fromObject(object);
                // ���String֧����
                return jsonArray.toString();
            }
        } catch (Exception e) {
            logger.warn("array����ת����Json�����쳣��", e);
            // Jsonת���쳣����null��
            return null;
        }
    }

    /**
     * ��Դ�ַ����е�������ţ�����ת����� 
     * Ŀǰ�����"\n","\r","\t","\\" �����⴦���������������չ��
     * 
     * @param source ת��ǰ��Դ�ַ���
     * @return ���������ַ�ת��֮����ַ���
     */
    public static String addTransferSign(String source) {

        if (StringUtil.isBlank(source)) {
            return source;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char chr = source.charAt(i);
            switch (chr) {
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(chr);
            }
        }
        return sb.toString();

    }

    /**
     * JSON�ַ�����ȡ������ֵ
     * <li>���磺
     * [{"key1":"5424235","key2":"2401"},
     * {"key1":"5424209","key2":"2335"}]
     * </li>
     * @param jsonString JSON��ʽ�ַ���
     * @param key JSON�����ֶ�,key1,key2
     * 
     * @return ���Զ�Ӧ��ֵ
     */
    public static Long[] getJsonvalueFromJsonstring(String jsonString, String key) {
        // ת��ΪJSON�����޷�ת���򷵻�""
        JSONArray jsonArray;
        try {
            jsonArray = JSONArray.fromObject(jsonString);
        } catch (Exception e) {
            logger.warn("�����JSONArray�ַ����Ƿ���" + jsonString);
            return null;
        }
        if (jsonArray == null || jsonArray.isEmpty()) {
            logger.warn("�����JSONArray�ַ������κ����ݣ�" + jsonString);
            return null;
        }

        Long values[] = new Long[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject;
            try {
                jsonObject = JSONObject.fromObject(jsonArray.get(i));
            } catch (Exception e) {
                logger.warn("�����JSONObject�ַ����Ƿ���" + jsonString);
                continue;
            }
            if (jsonObject == null || jsonObject.isEmpty()) {
                logger.warn("�����JSONObject�ַ������κ����ݣ�" + jsonString);
                continue;
            }
            long keyId = jsonObject.getLong(key);
            values[i] = keyId;
        }
        return values;
    }

    /**
     * JSON����ת����List<Map<String, String>>
     * 
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String, String>> parseJSON2List(String jsonStr) {
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while (it.hasNext()) {
            JSONObject json2 = it.next();
            list.add(convertJSON2Map(json2.toString()));
        }
        return list;
    }

    /**
     * ��{"memo":"","userId":"208811231321321234","userName":"Emily","userType":"1"}��ʽ��json�ַ����л�ȡָ����Key��ֵ
     * 
     * @param jsonString
     * @param key
     * @return Key��ֵ��Key�����ڷ���null
     */
    public static String getValueFromJson(String jsonString, String key) {
        if (StringUtil.isBlank(jsonString)) {
            return null;
        }

        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            logger.warn("�����JSONObject�ַ����Ƿ���" + jsonString);
            return null;
        }
        if (jsonObject == null || jsonObject.isEmpty() || !jsonObject.containsKey(key)) {
            logger.warn("�����JSONObject�ַ������κ����ݣ�" + jsonString);
            return null;
        }

        return jsonObject.getString(key);
    }

    /**
     * �����ַ�����������ֵ��
     * key1:value1,value2,value3;key2:value1,value2,value3
     * 
     * @param operatorType
     * @param configValue
     * @return
     */
    public static Map<String, String> getValueFromStr(String operatorType, String configValue) {
        //  �������õ���Ŀ������
        Map<String, String> map = new HashMap<String, String>();
        try {
            String[] configStr = StringUtil.split(configValue, ";");
            if (configStr == null) {
                return map;
            }
            for (String str : configStr) {
                String[] singleStr = StringUtil.split(str, ":");
                if (singleStr == null || singleStr.length != 2) {
                    return map;
                }
                String[] valueStr = StringUtil.split(singleStr[1], ",");
                if (valueStr == null) {
                    return map;
                }
                for (String g : valueStr) {
                    if (StringUtil.isNotEmpty(map.get(g))) {
                        logger.warn("��ǰ��Ŀ���ó����ظ�operatorType=", operatorType, "����ɫΪ��", map.get(g));
                    }
                    map.put(g, singleStr[0]);
                }
            }
        } catch (Exception e) {
            logger.error("������Ŀ���������쳣configValue=", configValue);
        }
        return map;
    }
}
