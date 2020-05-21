package com.javarush.task.task15.task1523;

public class SubSolution extends Solution {
    private int a;
    private int b;
    private String s;

    public SubSolution(int a) {
        this.a = a;
    }

    protected SubSolution(int a, int b, String s) {
        this.a = a;
        this.b = b;
        this.s = s;
    }

    SubSolution(int b, String s) {
        this.b = b;
        this.s = s;
    }
}
