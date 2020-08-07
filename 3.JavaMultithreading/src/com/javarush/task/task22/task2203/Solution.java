package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static class TooShortStringException extends Exception {
    }

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null) throw new TooShortStringException();
        int lengthString = string.length() - string.replaceAll("\t", "").length();
        if (lengthString < 2) {
            throw new TooShortStringException();
        }
        String array[] = string.split("\t");
        return array[1];
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
