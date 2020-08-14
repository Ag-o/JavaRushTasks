package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    public boolean isBusy() {
        return busy;
    }

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void startCookingOrder(Order order) {
        busy = true;

        ConsoleHelper.writeMessage(String.format("Start cooking - %s, cooking time %smin", order, order.getTotalCookingTime()));

        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {
        }

        StatisticManager.getInstance().register(new CookedOrderEventDataRow(
                order.toString(),
                this.name,
                order.getTotalCookingTime() * 60, order.getDishes()));
        setChanged();
        notifyObservers(order);

        busy = false;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (!queue.isEmpty()) {
                if (!isBusy()) {
                    Order pollOrder = queue.poll();
                    if (pollOrder != null)
                        startCookingOrder(pollOrder);
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
