package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {

        String fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        ArrayList<Integer> list = new ArrayList<>();

        try {
            while (fr.ready()) {
                int number = Integer.parseInt(fr.readLine());
                if (number % 2 == 0) {
                    list.add(number);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            fr.close();
        }

        Collections.sort(list);

        for (Integer l : list) {
            System.out.println(l);
        }
    }
}
