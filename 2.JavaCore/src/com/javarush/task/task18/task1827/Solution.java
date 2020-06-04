package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws Exception {
        String fileName;
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        scanner.close();

        if (args.length > 0) {
            if (args[0].equals("-c")) {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                int id;
                int max = 0;
                while (reader.ready()) {
                    id = Integer.parseInt(reader.readLine().substring(0, 8).trim());
                    if (id > max) max = id;
                }
                reader.close();
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
                String Sid = String.format("%-8d", ++max);
                String productName = String.format("%-30.30s", args[1]);
                String price = String.format(Locale.ROOT, "%-8.2f", Double.parseDouble(args[2]));
                String quantity = String.format("%-4d", Integer.parseInt(args[3]));
                writer.newLine();
                writer.write(Sid + productName + price + quantity);
                writer.close();
            }
        }
    }
}
