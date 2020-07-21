package com.javarush.task.task40.task4008;

/* 
Работа с Java 8 DateTime API
*/

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код
        String[] split = date.split(" ");
        String sdate = null;
        String stime = null;

        if (split.length == 1) {
            sdate = split[0].contains(".") ? split[0] : null;
            stime = split[0].contains(":") ? split[0] : null;
        }

        if (split.length == 2) {
            sdate = split[0].contains(".") ? split[0] : null;
            stime = split[1].contains(":") ? split[1] : null;
        }


        DateTimeFormatter formatter;

        if (sdate != null) {
            formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate dateTime = LocalDate.parse(sdate, formatter);

            WeekFields weekFields = WeekFields.of(Locale.getDefault());

            int weekOfTheMonthNumber = dateTime.get(weekFields.weekOfMonth());
            int weekOfTheYearNumber = dateTime.get(weekFields.weekOfYear());

            System.out.println("День: " + dateTime.getDayOfMonth());
            System.out.println("День недели: " + dateTime.getDayOfWeek().getValue());
            System.out.println("День месяца: " + dateTime.getDayOfMonth());
            System.out.println("День года: " + dateTime.getDayOfYear());
            System.out.println("Неделя месяца: " + weekOfTheMonthNumber);
            System.out.println("Неделя года: " + weekOfTheYearNumber);
            System.out.println("Месяц: " + dateTime.getMonthValue());
            System.out.println("Год: " + dateTime.getYear());
        }

        if (stime != null) {
            formatter = DateTimeFormatter.ofPattern("H:m:s");
            LocalTime dateTime = LocalTime.parse(stime, formatter);

            System.out.println("AM или PM: " + (dateTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"));
            System.out.println("Часы: " + dateTime.get(ChronoField.CLOCK_HOUR_OF_AMPM));
            System.out.println("Часы дня: " + dateTime.getHour());
            System.out.println("Минуты: " + dateTime.getMinute());
            System.out.println("Секунды: " + dateTime.getSecond());
        }
    }
}