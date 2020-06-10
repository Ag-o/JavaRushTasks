package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        FileOutputStream out = new FileOutputStream(fileName);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream inFile = new PrintStream(byteArrayOutputStream);

        PrintStream original = System.out;
        System.setOut(inFile);

        testString.printSomething();

        out.write(byteArrayOutputStream.toByteArray());
        out.close();

        inFile.close();
        System.setOut(original);
        System.out.println(byteArrayOutputStream.toString());

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
