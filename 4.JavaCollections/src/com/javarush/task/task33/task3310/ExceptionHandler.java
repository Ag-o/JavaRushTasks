package com.javarush.task.task33.task3310;

import static com.javarush.task.task33.task3310.Helper.printMessage;

public class ExceptionHandler {

    public static void log(Exception e) {
        printMessage(e.getMessage());
    }
}
