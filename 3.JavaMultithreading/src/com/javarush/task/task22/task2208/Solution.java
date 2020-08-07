package com.javarush.task.task22.task2208;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println(getQuery(map));
    }

    public static String getQuery(Map<String, String> params) {
        String result = "";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                result = sb.append(entry.getKey() + " = '" + entry.getValue() + "' and ").toString();
            }
        }
        if (!result.equals("")) {
            result = result.substring(0, result.length() - 5);
        }
        return result;
    }
}
