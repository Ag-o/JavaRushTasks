package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        File file = new File(reader.readLine());
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bf = new BufferedWriter(fileWriter);
        //while(!(line = bufferedReader.readLine()).equals("exit"))
        while (true) {
            String s = reader.readLine();
            if (s.equalsIgnoreCase("exit")) {
                bf.write(s);
                bf.close();
                break;
            } else {
                bf.write(s);
                bf.newLine();
            }
        }

        reader.close();
    }
}
