package com.javarush.task.task33.task3301;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

/* 
Первая сериализация в JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Cat cat = new Cat();
        cat.name = "Cat";
        cat.age = 5;
        cat.weight = 3;

        Dog dog = new Dog();
        dog.name = "Dog";
        dog.age = 8;
        dog.owner = "Artem";

        ArrayList<Pet> pets = new ArrayList<>();
        pets.add(cat);
        pets.add(dog);

        StringWriter writer = new StringWriter();
        convertToJSON(writer, pets);
        System.out.println(writer.toString());
    }

    public static void convertToJSON(StringWriter writer, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, object);
    }

    @JsonAutoDetect
    public static class Pet {
        public String name;
    }

    @JsonAutoDetect
    public static class Cat extends Pet {
        public int age;
        public int weight;
    }

    @JsonAutoDetect
    public static class Dog extends Pet {
        public int age;
        public String owner;
    }
}