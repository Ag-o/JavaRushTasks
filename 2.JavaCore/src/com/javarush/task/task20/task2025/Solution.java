package com.javarush.task.task20.task2025;

import java.util.ArrayList;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] result = null;

        try {
            ArrayList<Long> list = new ArrayList<>((int) N);
            long a = 0;

            for (long i = 0; i < N; i++) {
                String num = Long.toString(i);
                String[] array = num.split("");

                for (int j = 1; j <= array.length; j++) {

                    int el = Integer.parseInt(array[j - 1]);

                    a += (long) Math.pow(el, array.length);
                }
                if (a > 0 && a == i) list.add(a);
                a = 0;
            }
            result = new long[list.size()];

            for (int i = 0; i < result.length; i++) {
                System.out.println(list.get(i));
                result[i] = list.get(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        getNumbers(470);
    }
}
