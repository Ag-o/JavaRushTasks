package com.javarush.task.task27.task2708;

import java.util.Set;
import java.util.stream.IntStream;

/* 
Убираем deadLock используя открытые вызовы
*/
public class Solution {
    public static void main(String[] args) {
        final long deadline = System.currentTimeMillis() + 5000;

        final RealEstate realEstate = new RealEstate();
        Set<Apartment> allApartments = realEstate.getAllApartments();

        final Apartment[] apartments = allApartments.toArray(new Apartment[allApartments.size()]);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> IntStream.range(0, 10)
                    .forEach(count -> realEstate.revalidate()), "RealEstateThread" + i).start();

            new Thread(() -> {
                for (Apartment apartment : apartments) {
                    apartment.revalidate(true);
                }
            }, "ApartmentThread" + i).start();
        }

        Thread daemonThread = new Thread(() -> {
            while (System.currentTimeMillis() < deadline)
                try {
                    Thread.sleep(2);
                } catch (InterruptedException ignored) {
                }
            System.out.println("Deadlock occurred");
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}