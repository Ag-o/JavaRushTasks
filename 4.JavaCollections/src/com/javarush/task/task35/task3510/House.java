package com.javarush.task.task35.task3510;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* 
Вход воспрещен!
*/
public class House<T> {

    private List<T> residents = new ArrayList();

    public void enter(T resident) {
        residents.add(resident);
        //checkConflicts();
    }

    public void leave(T resident) {
        residents.remove(resident);
    }

    /*
    private void checkConflicts() {
        boolean conflict = false;
        for (Object resident : residents) {
            if (resident instanceof Dog) {
                conflict = true;
            }
        }

        if (conflict) {
            Iterator iterator = residents.iterator();
            while (iterator.hasNext()) {
                Object resident = iterator.next();
                if (resident instanceof Cat) {
                    iterator.remove();
                }
            }
        }
    }
    */

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("В доме находятся:\n");
        for (Object resident : residents) {
            builder.append(resident.toString()).append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Dog dog = new Dog("dog");
        Puppy puppy = new Puppy("puppy");
        Cat cat = new Cat("cat");
        Kitten kitten = new Kitten("kitten");

        House<Dog> dogHouse = new House();
        dogHouse.enter(dog);
        dogHouse.enter(puppy);
        //dogHouse.enter(cat);
        System.out.println(dogHouse);

        House<Cat> catHouse = new House();
        catHouse.enter(cat);
        catHouse.enter(kitten);
        //catHouse.enter(dog);
        System.out.println(catHouse);
    }
}
