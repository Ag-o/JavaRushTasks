package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = null;
        String file2 = null;
        try {
            file1 = reader.readLine();
            file2 = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader fileReader1 = new BufferedReader(new FileReader(file1));
            BufferedReader fileReader2 = new BufferedReader(new FileReader(file2));
            String line1;// = null;
            String line2;// = null;

            while (fileReader1.ready()) {
                line1 = fileReader1.readLine();
                allLines.add(line1);
            }
            fileReader1.close();
            while (fileReader2.ready()) {
                line2 = fileReader2.readLine();
                forRemoveLines.add(line2);
            }
            fileReader2.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Solution solution = new Solution();
        solution.joinData();
    }

    public void joinData() throws CorruptedDataException {

        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
