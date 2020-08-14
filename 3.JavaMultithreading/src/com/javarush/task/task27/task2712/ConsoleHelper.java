package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String result = "";
        try {
            result = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Dish> getAllDishesForOrder() {
        List<Dish> dishes = new ArrayList<>();
        System.out.println("Выберете блюдо");
        String request = readString();

        while (!request.equals("exit")) {
            if ((Dish.allDishesToString().contains(request))) {
                dishes.add(Dish.valueOf(request));
                System.out.println("Добавлено");
            } else {
                System.out.println("Такого блюда нет в меню");
            }
            request = readString();
        }
        System.out.println("создано");
        return dishes;
    }
}
