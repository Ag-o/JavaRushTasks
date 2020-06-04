package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileReader;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(args[0]);
        int space = 0;
        int size = 0;
        while (fileReader.ready()) {
            if (' ' == (char) fileReader.read()) {
                space++;
                size++;
            } else {
                size++;
            }
        }
        fileReader.close();
        System.out.format("%.2f", 1.0 * space / size * 100);
    }
}
