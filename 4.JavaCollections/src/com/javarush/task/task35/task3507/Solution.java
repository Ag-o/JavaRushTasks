package com.javarush.task.task35.task3507;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {

        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");

        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        List<File> modules = new ArrayList<>();
        File dir = new File(pathToAnimals);

        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".class")) {
                    modules.add(file);
                }
            }
        }

        for (File module : modules) {
            try {

                MyLoader loader = new MyLoader();

                Class clazz = loader.load(module);
                Constructor[] constructors = clazz.getConstructors();
                Class[] interfaces = clazz.getInterfaces();

                // находим конструктор без параметров
                boolean flag = false;
                for (Constructor constructor : constructors) {
                    if (constructor.getParameterCount() == 0) {
                        flag = true;
                        break;
                    }
                }
                // находим интерфейс, проверяем конструктор, создаем класс и добавляем его в список
                boolean flag1 = false;
                for (Class cl : interfaces) {
                    if (cl.equals(Animal.class)) {
                        flag1 = true;
                        break;
                    }
                }

                if (flag && flag1) {
                    Animal animal = (Animal) clazz.newInstance();
                    set.add(animal);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return set;
    }
}
