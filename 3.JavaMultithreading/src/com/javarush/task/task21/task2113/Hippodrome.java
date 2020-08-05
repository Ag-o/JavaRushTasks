package com.javarush.task.task21.task2113;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;

    public static Hippodrome game;

    public Hippodrome(List<Horse> list) {
        this.horses = list;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    void run() {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void move() {
        getHorses().forEach(Horse::move);
    }

    void print() {
        getHorses().forEach(Horse::print);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparingDouble(Horse::getDistance))
                .get();
    }

    public void printWinner() {
        System.out.printf("Winner is %s!%n", getWinner().getName());
    }

    public static void main(String[] args) {

        Horse horse1 = new Horse("1", 3.0, 0.0);
        Horse horse2 = new Horse("2", 3.0, 0.0);
        Horse horse3 = new Horse("3", 3.0, 0.0);
        List<Horse> list = Arrays.asList(horse1, horse2, horse3);

        game = new Hippodrome(list);
        game.run();
        game.printWinner();
    }
}
