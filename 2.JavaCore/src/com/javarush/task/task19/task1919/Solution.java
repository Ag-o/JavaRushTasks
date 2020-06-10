package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        TreeMap<String, Double> treeMap = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            while (reader.ready()) {
                String[] s = reader.readLine().split(" ");
                if (treeMap.containsKey(s[0])) {
                    treeMap.put(s[0], treeMap.get(s[0]) + Double.parseDouble(s[1]));
                } else {
                    treeMap.put(s[0], Double.parseDouble(s[1]));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Double> e : treeMap.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
}
