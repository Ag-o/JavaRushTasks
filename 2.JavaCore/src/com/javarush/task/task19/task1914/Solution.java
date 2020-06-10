package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream original = System.out;
        ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        PrintStream myPrint = new PrintStream(myOut);

        System.setOut(myPrint);
        testString.printSomething();
        System.setOut(original);

        String fromMethod = myOut.toString().replaceAll("\n", "");

        String[] elements = fromMethod.split(" ");

        int a = Integer.parseInt(elements[0]), b = Integer.parseInt(elements[2]), r = 0;

        if (elements[1].equals("+")) r = a + b;
        if (elements[1].equals("-")) r = a - b;
        if (elements[1].equals("*")) r = a * b;
        if (elements[1].equals("/")) r = a / b;

        System.out.print(fromMethod);
        System.out.print(r);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}
