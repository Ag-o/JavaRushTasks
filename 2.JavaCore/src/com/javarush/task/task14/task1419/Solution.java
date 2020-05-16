package com.javarush.task.task14.task1419;

import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;
        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код
        exceptions.add(new InstantiationException());
        exceptions.add(new NullPointerException());
        exceptions.add(new ArrayStoreException());
        exceptions.add(new NumberFormatException());
        exceptions.add(new ClassNotFoundException());
        exceptions.add(new NoSuchMethodException());
        try {
            Object o = new Object();
            int i = (int) o;
        } catch (ClassCastException e) {
            exceptions.add(e);
        }
        int array[] = {1, 2, 3};
        try {
            System.out.println(array[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            exceptions.add(e);
        }
        ArrayList<Integer> list = null;
        try {
            list = new ArrayList<>();
            list.get(1);
        } catch (IndexOutOfBoundsException e) {
            exceptions.add(e);
        }
    }
}