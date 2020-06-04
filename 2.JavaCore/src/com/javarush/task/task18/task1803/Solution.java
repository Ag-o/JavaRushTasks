package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ArrayList<Integer> arrayList = new ArrayList<>();

        while (fileInputStream.available() > 0) {
            int date = fileInputStream.read();
            arrayList.add(date);
        }
        fileInputStream.close();
        int frequency = Integer.MIN_VALUE;

        for (Integer list : arrayList) {
            int f = (Collections.frequency(arrayList, list));
            if (f > frequency) {
                frequency = f;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer list : arrayList) {
            if (Collections.frequency(arrayList, list) == frequency) {
                map.put(list, 0);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.print(entry.getKey() + " ");
        }
    }
}
