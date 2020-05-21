package com.javarush.task.task15.task1523;

/* 
Перегрузка конструкторов
*/

public class Solution {
    private int c;
    private int x;
    private String d;
    private String string;

    Solution() {

    }

    public Solution(int a) {
        this.c = a;
    }

    private Solution(int a, int b) {
        this.c = a;
        this.x = b;
    }

    protected Solution(int a, int b, String d) {

        this.c = a;
        this.x = b;
        this.d = d;
    }


    public static void main(String[] args) {

    }
}
