package com.oxygen.oxygenspring._common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oxygen.oxygenspring._common.exception.ApiException;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;

public class Utils {
    public static String unicodeDecode(String str) {
        String[] arr = str.split("\\\\u");
        StringBuilder text = new StringBuilder();

        text.append(arr[0]);

        for (int i = 1; i < arr.length; i++) {
            String[] subArr = arr[i].split("(?<=\\G.{4})");
            String unicode = subArr[0];
            String rest = arr[i].substring(unicode.length());

            char ch = (char) Integer.parseInt(unicode, 16);
            text.append(ch);
            text.append(rest);
        }

        return text.toString();
    }

    public static <T> String objectToJson(T object, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR, "Object To Json String 변환 실패 - " + e.getMessage());
        }
    }

    public static <T> T jsonToObject(String json, Class<T> clazz, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR, "Json String To Object 변환 실패 - " + e.getMessage());
        }
    }
}
