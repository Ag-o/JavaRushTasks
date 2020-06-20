package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws Exception {
        String resultFileName = args[0];
        String[] fileNamePart = new String[args.length - 1];
        for (int i = 1; i <= args.length - 1; i++) {
            fileNamePart[i - 1] = args[i];
        }
        Arrays.sort(fileNamePart);
        ArrayList<FileInputStream> list = new ArrayList<>();

        for (String s : fileNamePart)
            list.add(new FileInputStream(s));

        FileOutputStream outputStream = new FileOutputStream(resultFileName);
        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(list));
        ZipInputStream zipInputStream = new ZipInputStream(sequenceInputStream);
        byte[] buf = new byte[1024];
        while (zipInputStream.getNextEntry() != null) {
            int count;
            while ((count = zipInputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, count);
            }
        }
        zipInputStream.close();
        outputStream.flush();
        outputStream.close();
    }
}
