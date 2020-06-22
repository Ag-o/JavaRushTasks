package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        byte[] readText = new byte[text.length()];
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(fileName, "rw");
            raf.seek(number);
            raf.read(readText, 0, text.length()); // читаем текст в массив с 0 по длину текста
            if (Arrays.equals(readText, text.getBytes())) {
                //если длина равна пишем в конец "true"
                raf.seek(raf.length());
                raf.write("true".getBytes());
            } else {
                raf.seek(raf.length());
                raf.write("false".getBytes());
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
