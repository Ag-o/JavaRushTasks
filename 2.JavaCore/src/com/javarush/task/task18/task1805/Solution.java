package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileInputStream fileInputStream = new FileInputStream(fileName);

        ArrayList<Integer> arrayList = new ArrayList<>();

        TreeSet<Integer> set = new TreeSet<>();

        while (fileInputStream.available() > 0) {
            int date = fileInputStream.read();
            arrayList.add(date);
        }
        fileInputStream.close();
        Collections.sort(arrayList);

        for (Integer list : arrayList) {
            set.add(list);
        }

        for (Integer integer : set) {
            System.out.print(integer + " ");
        }
    }
}
