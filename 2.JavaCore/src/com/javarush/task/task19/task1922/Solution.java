package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        while (bufferedReader.ready()) {
            String lines = bufferedReader.readLine();
            String[] line = lines.split(" ");
            int count = 0;
            for (String w : words) {
                for (String l : line) {
                    if (w.equals(l)) count++;
                }
            }
            if (count == 2) {
                System.out.println(lines);
            }
        }
        bufferedReader.close();
    }
}
