package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        byte[][] matrix = a;
        int countRect = 0;
        System.out.println("Так выглядит вся матрица которую нам передали:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(" " + matrix[i][j] + " ");
                if (matrix[i][j] == 1) {
                    if (i < matrix.length - 1 && j < matrix.length - 1) {
                        if (matrix[i][j + 1] == 0 && matrix[i + 1][j] == 0 && matrix[i + 1][j + 1] == 0) {
                            countRect++;//правый нижний угол с трёх сторон окружён нулями
                        }
                    }
                }
            }
            System.out.println();
        }

        System.out.println("Так выглядит самая нижняя строка");
        for (int j = 0; j < matrix.length; j++) {
            System.out.print(" " + matrix[matrix.length - 1][j] + " ");
            if (matrix[matrix.length - 1][j] == 1 && j < matrix.length - 1) {  //если 1 и это не последняя ячейка
                if (matrix[matrix.length - 1][j + 1] == 0) {                 //есть ли в следующей (0)
                    countRect++;                                         //если есть то прямоугольник
                }
            }
        }
        System.out.println();
        System.out.println("Так выглядит самый правый столбец:");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(" " + matrix[i][matrix.length - 1] + " ");
            if (matrix[i][matrix.length - 1] == 1 && i < matrix.length - 1) {  //если в ячейке 1 и это не последняя ячейка
                if (matrix[i + 1][matrix.length - 1] == 0) {                 //смотрим есть ли в следующей 0
                    countRect++;                                         //если есть то прямоугольник
                }
            }
        }
        if (matrix[matrix.length - 1][matrix.length - 1] == 1)
            countRect++;    // если в правом нижнем углу 1 то прямоугольник

        System.out.println();
        System.out.println("countRect = " + countRect);
        System.out.println("____________________________________________");
        return countRect;
    }
}
