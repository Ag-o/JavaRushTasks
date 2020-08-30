package com.javarush.task.task30.task3009;

import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
        System.out.println(isPolindrom("1"));
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> answer = new HashSet<>();

        try {
            for (int i = 2; i <= 36; i++) {
                String temp = Integer.toString(Integer.parseInt(number), i) + "";
                if (isPolindrom(temp)) {
                    answer.add(i);
                }
            }

        } catch (NumberFormatException e) {
        }

        return answer;
    }

    private static boolean isPolindrom(String s) {
        return s.contentEquals(new StringBuilder(s).reverse());
    }
}