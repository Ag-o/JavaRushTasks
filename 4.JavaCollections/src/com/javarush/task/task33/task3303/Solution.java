package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        //Path path = Paths.get(fileName);
        //String jsonString = new String(Files.readAllBytes(path));
        //StringReader reader = new StringReader(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName), clazz);
    }

    public static void main(String[] args) {

    }
}
