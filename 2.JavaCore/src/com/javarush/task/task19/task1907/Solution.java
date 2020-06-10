package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<String> list = new ArrayList<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }

        int count = 0;
        Pattern pattern = Pattern.compile("\\bworld\\b");
        for (String s : list) {
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                count++;
            }
        }
        fileReader.close();
        bufferedReader.close();
        System.out.println(count);
    }
}
