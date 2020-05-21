package com.javarush.task.task15.task1530;

public class TeaMaker extends DrinkMaker {

    @Override
    void makeDrink() {
        super.makeDrink();
    }

    @Override
    void getRightCup() {
        System.out.println("Берем чашку для чая");
    }

    @Override
    void putIngredient() {
        System.out.println("Насыпаем чай");
    }

    @Override
    void pour() {
        System.out.println("Заливаем кипятком");
    }
}
