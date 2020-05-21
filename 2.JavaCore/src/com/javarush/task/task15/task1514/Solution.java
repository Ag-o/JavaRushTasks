package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static {
        labels.put(0.0, "0.0");
        labels.put(0.1, "0.1");
        labels.put(0.2, "0.2");
        labels.put(0.3, "0.3");
        labels.put(0.4, "0.4");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}