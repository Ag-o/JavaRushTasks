package com.javarush.task.task15.task1529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }

    static {
        //add your code here - добавьте код тут
        reset();
    }

    public static CanFly result;

    public static void reset() {
        //add your code here - добавьте код тут
        try {
            String parameter = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (parameter.equals("helicopter")) {
                result = new Helicopter();
            }
            if (parameter.equals("plane")) {
                result = new Plane(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
