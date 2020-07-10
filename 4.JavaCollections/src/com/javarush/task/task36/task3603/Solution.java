package com.javarush.task.task36.task3603;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* 
Поиск класса по описанию
*/
public class Solution {
    public static void main(String... args) {    //it's correct line
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("A");
        copyOnWriteArrayList.add("B");
        copyOnWriteArrayList.add("C");
        copyOnWriteArrayList.remove("B");

        List<String> strings = Arrays.asList("B", "C", "D", "B");

        copyOnWriteArrayList.addAllAbsent(strings);

        copyOnWriteArrayList.forEach(System.out::println);
        /* Expected output
A
C
B
D
         */
    }
}