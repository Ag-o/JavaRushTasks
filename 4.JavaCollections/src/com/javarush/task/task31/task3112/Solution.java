package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("C:\\Desktop\\test\\test.txt"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method
        URL url = new URL(urlString);
        Path tempPath = Files.createTempFile("url", null, null);
        Files.copy(url.openStream(), tempPath);
        //добавляет путь после этого пути
        Path path = downloadDirectory.resolve((Paths.get(url.getPath()).getFileName()));

        Files.move(tempPath, path);
        return path;
    }
}
