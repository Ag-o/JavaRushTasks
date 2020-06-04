package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();
        reader.close();
        FileInputStream fileInputStream = new FileInputStream(fileName2);
        FileOutputStream fileOutputStream = new FileOutputStream(fileName1, true);

        int oneByte;
        while ((oneByte = fileInputStream.read()) != -1) {
            fileOutputStream.write(oneByte);
        }
        fileInputStream.close();
        fileInputStream = new FileInputStream(fileName3);
        while ((oneByte = fileInputStream.read()) != -1) {
            fileOutputStream.write(oneByte);
        }
        fileInputStream.close();
        fileInputStream.close();
    }
}
