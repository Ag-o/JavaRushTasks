package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        File rootFile = new File(root);
        if (rootFile.isDirectory()) queue.add(rootFile);
        else if (rootFile.isFile()) list.add(rootFile.getAbsolutePath());
        while (!queue.isEmpty()) {
            for (File file : queue.poll().listFiles()) {
                if (file.isDirectory()) queue.add(file);
                else if (file.isFile()) list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    public static void main(String[] args) {

    }
}
