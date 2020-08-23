package com.javarush.task.task29.task2912;

public abstract class AbstractLogger implements Logger {
    int level;
    Logger next;

    @Override
    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }

    @Override
    public void setNext(Logger next) {
        this.next = next;
    }

    @Override
    public void info(String message) {
        if (level == Level.WARN) {
            System.out.println("Logging to console: " + message);
        }
        if (level == Level.INFO) {
            System.out.println("Logging to file: " + message);
        }
        if (level == Level.ERROR) {
            System.out.println("Send SMS to CEO: " + message);
        }
        if (level == Level.FATAL) {
            System.out.println("Call to director: " + message);
        }
    }
}
