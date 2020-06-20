package com.javarush.task.task31.task3101;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        try (FileOutputStream fileOutputStream = new FileOutputStream(allFilesContent)) {
            ArrayList<File> files = new ArrayList<>();

            fList(path.getAbsolutePath(), files);

            Collections.sort(files, Comparator.comparing(File::getName));

            for (File file : files) {
                FileInputStream fileInputStream = new FileInputStream(file);
                while (fileInputStream.available() > 0) {
                    fileOutputStream.write(fileInputStream.read());
                }
                fileOutputStream.write('\n');
                fileOutputStream.flush();
                fileInputStream.close();
            }
        } catch (Exception e) {

        }
    }

    public static void fList(String directory, ArrayList<File> list) {
        File file = new File(directory);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile() && f.length() <= 50) list.add(f);
            else if (f.isDirectory()) fList(f.getAbsolutePath(), list);
        }
    }
}
