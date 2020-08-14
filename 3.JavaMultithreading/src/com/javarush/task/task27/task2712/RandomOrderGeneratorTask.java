package com.javarush.task.task27.task2712;

import java.util.List;
import java.util.Random;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (tablets.isEmpty()) return;

        while (!Thread.currentThread().isInterrupted()) {
            Tablet tablet = tablets.get(new Random().nextInt(tablets.size() - 1));
            tablet.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
