package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1;
        String file2;
        ArrayList<Integer> list = new ArrayList<>();

        try {
            file1 = reader.readLine();
            file2 = reader.readLine();
            reader.close();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));

            int in;
            while ((in = bufferedReader.read()) != -1) {
                //bufferedReader.skip(1);

                list.add(in);

                //bufferedWriter.write(in);
            }

            for (int i = 1; i < list.size(); i += 2) {
                bufferedWriter.write(list.get(i));
            }
            bufferedReader.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
