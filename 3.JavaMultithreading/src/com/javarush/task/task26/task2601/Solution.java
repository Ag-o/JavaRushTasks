package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

        Integer[] array = {13, 8, 15, 5, 17};
        Arrays.stream(sort(array)).forEach(result -> System.out.print(result + " "));
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        int median;
        Arrays.sort(array);
        if (array.length % 2 == 0) {
            median = (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        } else median = array[array.length / 2];

        Arrays.sort(array, Comparator.comparingInt(x -> Math.abs(median - x)));
        return array;
    }
}
