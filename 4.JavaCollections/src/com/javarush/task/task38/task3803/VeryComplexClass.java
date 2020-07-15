package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.Date;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o = "string";
        int i = (Integer) o;
    }

    public void methodThrowsNullPointerException() {
        Date empty = null;
        empty.getTime();
    }

    public static void main(String[] args) {

    }
}
