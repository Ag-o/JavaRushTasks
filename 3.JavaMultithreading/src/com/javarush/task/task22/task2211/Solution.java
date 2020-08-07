package com.javarush.task.task22.task2211;

import java.io.*;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //BufferedReader reader = Files.newBufferedReader(Paths.get(args[0]));
        //BufferedWriter writer = Files.newBufferedWriter(Paths.get(args[1]), utf8);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "Windows-1251"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));

        while (br.ready()) {
            int i = br.read();
            bw.write(i);
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
