package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            this.wheels = new ArrayList<Wheel>();

            String[] tmp = loadWheelNamesFromDB();

            if (tmp.length != 4) throw new IllegalArgumentException();

            for (int i = 0; i < tmp.length; i++) {
                wheels.add(Wheel.valueOf(tmp[i]));
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
        System.out.println(Wheel.valueOf("FRONT_LEFT"));
        System.out.println(new Car().wheels.get(0));
    }
}
