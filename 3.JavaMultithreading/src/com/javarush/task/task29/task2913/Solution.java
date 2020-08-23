package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        String result = "";
        StringBuilder stringBuilder = new StringBuilder();

        if (a < b) {
            for (int i = a; i <= b; i++) {
                stringBuilder.append(i).append(" ");
            }
            result = stringBuilder.toString().trim();
        }
        if (a > b) {
            for (int i = a; i >= b; i--) {
                stringBuilder.append(i).append(" ");
            }
            result = stringBuilder.toString().trim();
        }
        return result;
//        if (a > b) {
//            return a + " " + recursion(a - 1, b);
//        } else {
//            if (a == b) {
//                return Integer.toString(a);
//            }
//            return a + " " + recursion(a + 1, b);
//        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}