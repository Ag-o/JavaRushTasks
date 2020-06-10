package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String fileIn = br.readLine();
        String fileOut = br.readLine();
        br.close();
        FileReader fileReader = new FileReader(fileIn);
        BufferedReader riderIn = new BufferedReader(fileReader);
        String result = "";
        while (riderIn.ready()) {
            result += riderIn.readLine().replaceAll("[\\p{Punct}]", "");
        }

        fileReader.close();
        riderIn.close();

        FileWriter fileWriter = new FileWriter(fileOut);
        BufferedWriter writerOut = new BufferedWriter(fileWriter);
        writerOut.write(result);
        writerOut.flush();

        fileWriter.close();
        writerOut.close();
    }
}
