package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        try {
            String file = reader.readLine();
            reader.close();
            FileInputStream fileInputStream = new FileInputStream(file);
            int count = 0;
            while (fileInputStream.available() > 0) {
                int data = fileInputStream.read();
                if (data == ',') {
                    count++;
                }
            }

            input.close();
            fileInputStream.close();
            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
