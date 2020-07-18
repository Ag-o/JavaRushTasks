package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {

        int[][] matrix1 = {
                {1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1, 0}};  // maxSquare = 9

        int[][] matrix2 = {
                {0, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1},
                {0, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 1, 0}};  // maxSquare = 4

        int[][] matrix3 = {{0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1},
                {1, 1, 0},
                {0, 1, 1, 0, 1, 1},
                {1}};  // maxSquare = 4

        System.out.println("Must be 9: " + maxSquare(matrix1));
        System.out.println("Must be 4: " + maxSquare(matrix2));
        System.out.println("Must be 4: " + maxSquare(matrix3));
    }

    public static int maxSquare(int[][] matrix) {

        int square = 0;

        int i, j;

        int row = matrix.length;
        int column = matrix[0].length;
        int[][] s = new int[row][column];

        int max_of_s, max_i, max_j;

        for (i = 0; i < row; i++) s[i][0] = matrix[i][0];

        for (j = 0; j < column; j++) s[0][j] = matrix[0][j];

        for (i = 1; i < row; i++) {
            for (j = 1; j < column; j++) {
                if (matrix[i][j] == 1)
                    s[i][j] = Math.min(s[i][j - 1],
                            Math.min(s[i - 1][j], s[i - 1][j - 1])) + 1;
                else
                    s[i][j] = 0;
            }
        }

        max_of_s = s[0][0];
        max_i = 0;
        max_j = 0;
        for (i = 0; i < row; i++) {
            for (j = 0; j < column; j++) {
                if (max_of_s < s[i][j]) {
                    max_of_s = s[i][j];
                    max_i = i;
                    max_j = j;
                }
            }
        }
        for (i = max_i; i > max_i - max_of_s; i--) {
            for (j = max_j; j > max_j - max_of_s; j--) {
                square++;
            }
        }
        return square;
    }
}
