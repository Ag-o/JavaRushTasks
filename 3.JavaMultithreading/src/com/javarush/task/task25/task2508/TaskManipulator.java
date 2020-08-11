package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread target;

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(target.getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void start(String threadName) {
        target = new Thread(this, threadName);
        target.start();
    }

    @Override
    public void stop() {
        target.interrupt();
    }
}
