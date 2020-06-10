package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {

        ArrayList<String> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(args[0]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()) {
                String[] strings = bufferedReader.readLine().split(" ");
                for (String s : strings) {
                    if (s.length() > 6) {
                        list.add(s);
                    }
                }
            }
            fileReader.close();
            bufferedReader.close();

            FileWriter fileWriter = new FileWriter(args[1]);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    bufferedWriter.write(list.get(i));
                } else {
                    bufferedWriter.write(list.get(i) + ",");
                }
            }
            fileWriter.close();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
