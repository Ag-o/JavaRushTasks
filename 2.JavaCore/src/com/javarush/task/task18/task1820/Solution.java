package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        FileInputStream fileInputStream = new FileInputStream(fileName1);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName2);
        byte[] bytes = new byte[fileInputStream.available()];

        while (fileInputStream.available() > 0) {
            fileInputStream.read(bytes);
        }
        String s = new String(bytes, "UTF-8");
        String[] array = s.split(" ");

        for (int i = 0; i < array.length; i++) {
            double parseDouble = Double.parseDouble(array[i]);
            long round = Math.round(parseDouble);
            String result = round + " ";
            fileOutputStream.write(result.getBytes());

        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
