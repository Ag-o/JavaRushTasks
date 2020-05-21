package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
        String url = reader.readLine();
        String[] after = url.split("[?&]");

        for (String string : after) {
            System.out.println(string.replaceAll("=.*$", "") + " ");
            //обрезаем правую часть включительно от "="
        }

        System.out.println();

        for (int i = 1; i < after.length; i++) {
            String temp = after[i].replaceAll("=.*$", "");
            //Обрезаем правую часть до первого знака = (включительно)
            if (temp.equals("obj")) {
                String alert = after[i].replaceAll("^[^=]*=", "");
                //Обрезаем левую часть до первого знака = (включительно)
                try {
                    alert(Double.parseDouble(alert));
                } catch (NumberFormatException e) {
                    alert(alert);
                }
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
