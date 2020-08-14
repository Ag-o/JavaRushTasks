package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public Tablet getTablet() {
        return tablet;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public int getTotalCookingTime() {
        int sum = 0;
        for (Dish s : dishes) {
            int duration = s.getDuration();
            sum += duration;
        }
        return sum;
    }

    public boolean isEmpty() {
        if (dishes.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : String.format("Your order: %s of %s, cooking time %dmin", dishes, tablet, getTotalCookingTime());
    }
}
