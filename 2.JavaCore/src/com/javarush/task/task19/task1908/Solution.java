package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileIn = reader.readLine();
        String fileOut = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(fileIn);
        BufferedReader in = new BufferedReader(fileReader);


        FileWriter fileWriter = new FileWriter(fileOut);
        BufferedWriter out = new BufferedWriter(fileWriter);

        while (in.ready()) {
            String[] result = in.readLine().split(" ");
            for (String s : result) {
                try {
                    int i = Integer.parseInt(s);
                    out.write(i + " ");
                    out.flush();
                } catch (NumberFormatException e) {

                }

            }
        }

        fileReader.close();
        fileWriter.close();
        in.close();
        out.close();
    }
}
