package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = fileReader.readLine()) != null) {
                stringBuilder.append(line + " ");
            }
        } finally {
            fileReader.close();
        }
        StringBuilder result = getLine(stringBuilder.toString().trim().split(" "));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words == null || words.length == 0) {
            return new StringBuilder();
        }
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<StringBuilder> listOfBuilders = new ArrayList<>();
        String[] cloneWords = words.clone();
        TreeSet<String> set = new TreeSet<>();
        for (int i = 0; i < words.length; i++) {
            set.add(words[i]);
        }
        for (String stringFromSet : set) {
            for (int i = 0; i < words.length; i++) {
                cloneWords[i] = words[i];
            }
            StringBuilder tempBuilder = new StringBuilder();
            String first = stringFromSet;
            tempBuilder.append(first + " ");
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words.length; j++) {
                    String second = cloneWords[j];
                    if (!first.equals("+") && !second.equals("+")) {
                        char[] firstChars = first.toLowerCase().toCharArray();
                        char[] secondChars = second.toLowerCase().toCharArray();
                        if (firstChars[firstChars.length - 1] == secondChars[0]) {
                            tempBuilder.append(second + " ");
                            for (int r = 0; r < words.length; r++) {
                                if (first.equals(cloneWords[r])) {
                                    cloneWords[r] = "+";
                                }
                            }
                            for (int r = 0; r < words.length; r++) {
                                if (second.equals(cloneWords[r])) {
                                    cloneWords[r] = "+";
                                }
                            }
                            first = second;
                        }
                    }
                }
            }
            listOfBuilders.add(tempBuilder);
        }
        stringBuilder = listOfBuilders.get(0);
        for (StringBuilder builder : listOfBuilders) {
            if (stringBuilder.length() < builder.length()) {
                stringBuilder = builder;
            }
        }
        return stringBuilder;
    }
}