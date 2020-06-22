package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]); //2) number - число, позиция в файле;
        String text = args[2];

        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(fileName, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {

            if (raf.length() < number) {
                raf.seek(raf.length());
                raf.write(text.getBytes());
            } else {
                raf.seek(number);
                raf.write(text.getBytes());
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
