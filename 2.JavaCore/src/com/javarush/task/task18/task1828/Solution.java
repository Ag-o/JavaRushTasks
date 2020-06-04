package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        String fileName = "C:\\JavaRush_my_project\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1828\\text";

        if (args.length > 0) {
            if (args[0].equals("-u")) {
                String argsLine = String.format("%-8s%-30.30s%-8.2f%-4d",
                        args[1],
                        args[2],
                        Double.parseDouble(args[3]),
                        Integer.parseInt(args[4]));

                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                ArrayList<String> list = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    if ((line.substring(0, 8).trim()).equals(argsLine.substring(0, 8).trim())) {
                        list.add(argsLine);
                    } else list.add(line);
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                for (int i = 0; i < list.size(); i++) {
                    writer.write(list.get(i));
                    if (i < list.size() - 1) writer.write("\n");
                }
                writer.close();
                list.clear();
            }
            if (args[0].equals("-d")) {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                ArrayList<String> list = new ArrayList<>();
                while (reader.ready()) {
                    list.add(reader.readLine());
                }
                reader.close();
                String id = String.format("%-8s", args[1]);

                for (String s : list) {
                    if (s.startsWith(id)) {
                        list.remove(s);
                    }
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                for (int i = 0; i < list.size(); i++) {
                    writer.write(list.get(i));
                    if (i != list.size() - 1) writer.write("\n");
                }
                writer.close();
            }
        }
    }
}
