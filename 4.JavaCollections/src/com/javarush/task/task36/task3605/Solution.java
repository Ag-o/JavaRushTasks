package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> characters = new TreeSet<>();
        FileReader fileReader = new FileReader(args[0]);
        int n;
        while ((n = fileReader.read()) != -1) {
            if (Character.isLetter((char) n)) {
                characters.add((char) n);
            }
        }
        Character character;
        for (int i = 0; i < 5; i++) {
            character = characters.pollFirst();
            if (character != null)
                System.out.print(character);
        }

        /*
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            TreeSet<String> stringSet = new TreeSet<>();

            Stream<String> stream = reader.lines();
            stringSet.addAll(stream.map(s -> s.split(""))
                    .flatMap(Arrays::stream)
                    .filter(c -> c.matches("[A-Za-z]"))
                    .map(s -> s.toLowerCase())
                    .collect(Collectors.toSet()));

            stringSet.stream().limit(5).forEach(System.out::print);
        }
        */
    }
}
