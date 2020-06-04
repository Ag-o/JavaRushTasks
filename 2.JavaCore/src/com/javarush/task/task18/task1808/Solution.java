package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            FileInputStream fileInputStream = new FileInputStream(reader.readLine());
            FileOutputStream fileOutputStream = new FileOutputStream(reader.readLine());
            FileOutputStream fileOutputStream1 = new FileOutputStream(reader.readLine());
            reader.close();

            byte[] buffer = new byte[fileInputStream.available()];
            System.out.println(buffer.length);

            while (fileInputStream.available() > 0) {
                fileInputStream.read(buffer);
            }
            fileInputStream.close();

            if (buffer.length % 2 == 0) {
                for (int i = buffer.length / 2; i < buffer.length; i++) {
                    fileOutputStream1.write(buffer[i]);
                }
                for (int i = 0; i < buffer.length / 2; i++) {
                    fileOutputStream.write(buffer[i]);
                }
            } else {
                for (int i = (buffer.length / 2) + 1; i < buffer.length; i++) {
                    fileOutputStream1.write(buffer[i]);
                }
                for (int i = 0; i < (buffer.length / 2) + 1; i++) {
                    fileOutputStream.write(buffer[i]);
                }
            }

            fileOutputStream.close();
            fileOutputStream1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
