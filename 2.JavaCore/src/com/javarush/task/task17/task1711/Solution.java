package com.javarush.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws Exception {
        //start here - начни тут

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        switch (args[0]) {
            case "-c": {
                synchronized (allPeople) {
                    String name;
                    Sex sex;
                    Date bd;
                    Person p;
                    for (int i = 1; i < args.length; i = i + 3) {
                        name = args[i];
                        sex = (args[i + 1] == "м") ? Sex.MALE : Sex.FEMALE;
                        bd = format.parse(args[i + 2]);
                        if (sex == Sex.MALE) {
                            p = Person.createMale(name, bd);
                        } else {
                            p = Person.createFemale(name, bd);
                        }
                        allPeople.add(p);
                        System.out.println(allPeople.indexOf(p));
                    }
                    break;
                }
            }
            case "-u": {
                synchronized (allPeople) {
                    String name;
                    Sex sex;
                    Date bd;
                    Person p;
                    int id;
                    for (int i = 1; i < args.length; i = i + 4) {
                        id = Integer.parseInt(args[i]);
                        name = args[i + 1];
                        sex = (args[i + 2] == "м") ? Sex.MALE : Sex.FEMALE;
                        bd = format.parse(args[i + 3]);
                        p = allPeople.get(id);
                        p.setName(name);
                        p.setSex(sex);
                        p.setBirthDate(bd);
                    }
                    break;
                }
            }
            case "-d": {
                synchronized (allPeople) {
                    Person p;
                    int id;
                    for (int i = 1; i < args.length; i = i + 1) {
                        id = Integer.parseInt(args[i]);
                        p = allPeople.get(id);
                        p.setSex(null);
                        p.setBirthDate(null);
                        p.setName(null);
                    }
                    break;
                }
            }
            case "-i": {
                synchronized (allPeople) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                    Person p;
                    int id;
                    for (int i = 1; i < args.length; i = i + 1) {
                        id = Integer.parseInt(args[i]);
                        p = allPeople.get(id);
                        String sex = (p.getSex() == Sex.MALE) ? "м" : "ж";
                        System.out.println(p.getName() + " " + sex + " " + sdf.format(p.getBirthDate()));
                    }
                    break;
                }
            }
            default: {
                synchronized (allPeople) {
                }
                break;
            }
        }
    }
}
