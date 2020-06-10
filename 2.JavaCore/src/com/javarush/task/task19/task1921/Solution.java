package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
        String line;
        String date;
        while (reader.ready()) {
            line = reader.readLine();
            String name = line.replaceAll("\\d", "").trim();
            date = line.replaceAll(name, "").trim();

            PEOPLE.add(new Person(name, simpleDateFormat.parse(date)));
        }
        reader.close();
    }
}
