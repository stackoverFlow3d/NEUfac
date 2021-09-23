package com.neu.fac.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JsonUtils {
    public static <T> T jsonToObject(String json,Class<T> clazz){
        T object = JSONObject.parseObject(json,clazz);
        return object;
    }
    public static <T> String objectToJson(T object){
        String json = JSONObject.toJSONString(object);
        return json;
    }
    public static <T> List<T> jsonToList(String json, Class<T> clazz){
        List<T> list = JSONObject.parseArray(json,clazz);
        return list;
    }
    public static <T> String listToJson(List<T> list){
        String json = JSONObject.toJSONString(list);
        return json;
    }
}

