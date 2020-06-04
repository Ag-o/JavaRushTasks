package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream(args[0]);

        int count = 0;
        while (fileInputStream.available() > 0) {
            int i = fileInputStream.read();
            if ((i >= 97 && i <= 122) || (i >= 65 && i <= 90)) {
                count++;
            }
        }
        fileInputStream.close();
        System.out.println(count);
    }
}
