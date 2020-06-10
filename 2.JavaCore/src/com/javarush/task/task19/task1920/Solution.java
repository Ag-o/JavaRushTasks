package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        HashMap<String, Double> map = new HashMap<>();
        while (reader.ready()) {
            String s = reader.readLine();
            String[] strs = s.split("[\\s\\t\\n\\x0B\\f\\r]");
            String key = strs[0];
            double value = Double.parseDouble(strs[1]);
            if (map.containsKey(key)) {
                map.put(key, map.get(strs[0]) + value);
            } else {
                map.put(key, value);
            }
        }
        reader.close();
        double maxValue = Double.MIN_VALUE;
        for (Double a : map.values()) {
            if (maxValue < a) {
                maxValue = a;
            }
        }
        for (Map.Entry<String, Double> pair : map.entrySet()) {
            if (pair.getValue().equals(maxValue)) {
                System.out.println(pair.getKey());
            }
        }
    }
}
