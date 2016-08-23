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
 * JSON工具类
 * 
 * @author longlong.gu
 * @version $Id: JsonUtil.java, v 0.1 2012-9-6 上午09:42:40 longlong.gu Exp $
 */
public class JsonUtil {

    /** 普通日志记录器 */
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * JSON数据转换成Map.用于解析http请求的响应数据
     * 
     * @param jsonString
     *            数组格式JSON数据
     * @return
     */
    public static Map<String, String> convertJSON2LinkedHashMap(String jsonString) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        return toMap(jsonString, resultMap);
    }

    /**
     * JSON数据转换成Map.用于解析http请求的响应数据
     * 
     * @param jsonString
     *            数组格式JSON数据
     * @return
     */
    public static Map<String, String> convertJSON2Map(String jsonString) {

        Map<String, String> resultMap = new HashMap<String, String>();
        return toMap(jsonString, resultMap);

    }

    /**
     * 转换成map
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
        // 转换为JSON对象，无法转换则返回""
        JSONObject json = null;
        try {
            json = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            logger.warn("传入的JSON字符串非法：" + jsonString);
            return resultMap;
        }

        if (json == null || json.isEmpty()) {
            logger.warn("传入的JSON字符串无任何数据：" + jsonString);
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
     * JSON数据转换成LIST
     * 
     * @param jsonString
     *            数组格式JSON数据
     * @param className
     *            包路径+类名
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

            // 如果JSON串为空的话，不会抛异常，会返回一个JSONNull对象放入JSON数组中。
            JSONArray jarr = JSONArray.fromObject(jsonString);

            object = Class.forName(className).newInstance();

            list = (List) JSONArray.toCollection(jarr, object.getClass());

        } catch (Exception e) {
            logger.error("JSON数据转换异常", jsonString);
        }
        return list;
    }

    /**
     * 把数据对象转换成json字符串 DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
     * 数组对象形如：[{}, {}, {}, ...] map对象形如：{key1 : {"id" : idValue, "name" :
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
     * 将array对象转换成Json对象
     * 
     * @author sibai.zhang
     * 
     * @param object 
     *          传入Object对象 （为空返回null）
     * @param filterProperty
     *          过滤属性，过滤掉可能产生的递归属性。（为空不检测）
     * @return 
     *          Json形式的字符串，如果转换异常，返回null。
     */
    public static String arrayObject2Json(Object object, final List<String> filterProperty) {

        // 传如对象为空返回null;
        if (object == null) {
            return null;
        }
        try {
            if (!CollectionUtils.isEmpty(filterProperty)) {
                // Json配置
                JsonConfig config = new JsonConfig();
                config.setJsonPropertyFilter(new PropertyFilter() {
                    /**
                     * 配置可能出现递归的属性
                     * @see net.sf.json.util.PropertyFilter#apply(java.lang.Object, java.lang.String, java.lang.Object)
                     */
                    public boolean apply(Object source, String name, Object value) {
                        // 配置可能出现递归的属性
                        if (filterProperty.contains(name)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                // 将pojo对象转换成Json对象
                JSONArray jsonArray = JSONArray.fromObject(object, config);
                // 输出String支付串
                return jsonArray.toString();
            } else {
                // 将pojo对象转换成Json对象
                JSONArray jsonArray = JSONArray.fromObject(object);
                // 输出String支付串
                return jsonArray.toString();
            }
        } catch (Exception e) {
            logger.warn("array对象转换成Json对象异常：", e);
            // Json转换异常返回null。
            return null;
        }
    }

    /**
     * 对源字符串中的特殊符号，增加转义符。 
     * 目前仅针对"\n","\r","\t","\\" 做特殊处理，后续有需求可扩展。
     * 
     * @param source 转换前的源字符串
     * @return 增加特殊字符转义之后的字符串
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
     * JSON字符串中取出属性值
     * <li>例如：
     * [{"key1":"5424235","key2":"2401"},
     * {"key1":"5424209","key2":"2335"}]
     * </li>
     * @param jsonString JSON格式字符串
     * @param key JSON属性字段,key1,key2
     * 
     * @return 属性对应的值
     */
    public static Long[] getJsonvalueFromJsonstring(String jsonString, String key) {
        // 转换为JSON对象，无法转换则返回""
        JSONArray jsonArray;
        try {
            jsonArray = JSONArray.fromObject(jsonString);
        } catch (Exception e) {
            logger.warn("传入的JSONArray字符串非法：" + jsonString);
            return null;
        }
        if (jsonArray == null || jsonArray.isEmpty()) {
            logger.warn("传入的JSONArray字符串无任何数据：" + jsonString);
            return null;
        }

        Long values[] = new Long[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject;
            try {
                jsonObject = JSONObject.fromObject(jsonArray.get(i));
            } catch (Exception e) {
                logger.warn("传入的JSONObject字符串非法：" + jsonString);
                continue;
            }
            if (jsonObject == null || jsonObject.isEmpty()) {
                logger.warn("传入的JSONObject字符串无任何数据：" + jsonString);
                continue;
            }
            long keyId = jsonObject.getLong(key);
            values[i] = keyId;
        }
        return values;
    }

    /**
     * JSON数据转换成List<Map<String, String>>
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
     * 从{"memo":"","userId":"208811231321321234","userName":"Emily","userType":"1"}格式的json字符串中获取指定的Key的值
     * 
     * @param jsonString
     * @param key
     * @return Key的值，Key不存在返回null
     */
    public static String getValueFromJson(String jsonString, String key) {
        if (StringUtil.isBlank(jsonString)) {
            return null;
        }

        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            logger.warn("传入的JSONObject字符串非法：" + jsonString);
            return null;
        }
        if (jsonObject == null || jsonObject.isEmpty() || !jsonObject.containsKey(key)) {
            logger.warn("传入的JSONObject字符串无任何数据：" + jsonString);
            return null;
        }

        return jsonObject.getString(key);
    }

    /**
     * 解析字符串类似如下值：
     * key1:value1,value2,value3;key2:value1,value2,value3
     * 
     * @param operatorType
     * @param configValue
     * @return
     */
    public static Map<String, String> getValueFromStr(String operatorType, String configValue) {
        //  解析配置的类目场景：
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
                        logger.warn("当前类目配置出现重复operatorType=", operatorType, "，角色为：", map.get(g));
                    }
                    map.put(g, singleStr[0]);
                }
            }
        } catch (Exception e) {
            logger.error("解析类目场景配置异常configValue=", configValue);
        }
        return map;
    }
}
