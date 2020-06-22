package com.javarush.task.task32.task3204;

import java.io.*;
import java.util.stream.IntStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        InputStream inputStream = new ByteArrayInputStream(passWord().getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        try {
            while (bis.available() > 0) {
                int data = bis.read();
                outputStream.write(data);
            }

        } catch (IOException e) {
        }

        return outputStream;
    }

    public static String passWord() {
        String base = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        String str1 = "0123456789";
        String str2 = "qwertyuiopasdfghjklzxcvbnm";
        String str3 = "QWERTYUIOPASDFGHJKLZXCVBNM";

        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        char[] chars = base.toCharArray();
        char[] passChar = new char[8];

        IntStream.range(0, 8).forEachOrdered(i -> {
            int z = (int) (Math.random() * 61);
            passChar[i] = chars[z];
        });

        for (char c : passChar) {
            if (str1.contains(Character.toString(c))) flag1 = true;
            else if (str2.contains(Character.toString(c))) flag2 = true;
            else if (str3.contains(Character.toString(c))) flag3 = true;
        }
        if (flag1 && flag2 && flag3) {
            return String.valueOf(passChar);
        }
        return passWord();
    }
}