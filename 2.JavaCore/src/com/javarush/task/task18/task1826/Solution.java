package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        if (args[0].equals("-e")) {
            String fileName = args[1];
            String fileOutputName = args[2];
            FileInputStream fis = new FileInputStream(fileName);
            ArrayList<Byte> list = new ArrayList<>();
            while (fis.available() > 0) {
                list.add((byte) (fis.read() + 1));
            }
            fis.close();
            FileOutputStream fos = new FileOutputStream(fileOutputName);
            for (int i = 0; i < list.size(); i++) {
                fos.write(list.get(i));
            }
            fos.close();
            list.clear();
        }
        if (args[0].equals("-d")) {
            FileInputStream fis = new FileInputStream(args[1]);
            byte[] array = new byte[fis.available()];
            int size = fis.available();

            while (fis.available() > 0) {
                for (int i = 0; i < size; i++) {
                    array[i] = (byte) (fis.read() - 1);
                }
            }
            fis.close();
            FileOutputStream fos = new FileOutputStream(args[2]);
            for (int i = 0; i < size; i++) {
                fos.write(array[i]);
            }
            fos.close();
        }
    }
}
