package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые редкие байты
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
        int frequency = Integer.MAX_VALUE;
        for (Integer list : arrayList) {
            int f = (Collections.frequency(arrayList, list));
            if (f < frequency) {
                frequency = f;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (Integer list : arrayList) {
            if (Collections.frequency(arrayList, list) == frequency) {
                result.add(list);
            }
        }

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
