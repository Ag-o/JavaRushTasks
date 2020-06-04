package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName1);
        byte[] file1 = new byte[inputStream.available()];
        while (inputStream.available() > 0) {
            inputStream.read(file1);
        }
        inputStream.close();
        FileInputStream inputStream2 = new FileInputStream(fileName2);

        byte[] file2 = new byte[inputStream2.available()];

        while (inputStream2.available() > 0) {
            inputStream2.read(file2);
        }
        inputStream2.close();

        FileOutputStream fileOutputStream = new FileOutputStream(fileName1);
        fileOutputStream.write(file2);
        fileOutputStream.write(file1);
        fileOutputStream.close();
    }
}
