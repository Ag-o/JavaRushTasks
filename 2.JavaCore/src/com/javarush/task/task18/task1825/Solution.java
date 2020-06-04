package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.TreeSet;

/* 
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeSet<String> set = new TreeSet<>();
        String fileIn;
        while (!(fileIn = reader.readLine()).equals("end")) {
            set.add(fileIn);
        }
        reader.close();
        String fileOut = set.first().substring(0, set.first().lastIndexOf(".part"));
        FileOutputStream out = new FileOutputStream(fileOut);
        for (String s : set) {
            FileInputStream in = new FileInputStream(s);
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            out.write(buffer);
            in.close();
        }
        out.close();
    }
}
