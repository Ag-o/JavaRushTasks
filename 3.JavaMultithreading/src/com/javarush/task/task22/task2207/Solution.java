package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        String fileName = null;
        try {
            fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileName == null || fileName.isEmpty()) return;

        ArrayList<String> wordsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = br.readLine()) != null) {
                for (String word : str.split("\\s+")) {
                    if (!word.isEmpty())
                        wordsList.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int wordsSize = wordsList.size();
        for (int i = 0; i < wordsSize; i++) {
            String reversed = new StringBuilder(wordsList.get(i)).reverse().toString();
            for (int j = i + 1; j < wordsSize; j++) {
                if (reversed.equals(wordsList.get(j))) {
                    Pair pair1 = new Pair(wordsList.get(i), wordsList.get(j));
                    Pair pair2 = new Pair(wordsList.get(j), wordsList.get(i));
                    if (!result.contains(pair1) && !result.contains(pair2)) {
                        result.add(pair1);
                    }
                }
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
