package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    private final static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Waiter waiter = new Waiter();

        List<Cook> cookList = new LinkedList<>();
        for (int i = 1; i < 3; i++) {
            Cook cook = new Cook("Вася" + i);
            cook.setQueue(orderQueue);
            cook.addObserver(waiter);
            cookList.add(cook);
        }

        List<Tablet> tabletList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setOrderQueue(orderQueue);
            tabletList.add(tablet);
        }

        DirectorTablet directorTablet = new DirectorTablet();

        Thread thread = new Thread(new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        thread.interrupt();

        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();
    }
}
