package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public void printAdvertisementProfit() {
        double total = 0.00;
        TreeMap<Date, Long> treeMap = StatisticManager.getInstance().AdvertisementProfit();
        for (Date date : treeMap.keySet()) {
            double count;
            count = (double) treeMap.get(date) / 100;
            ConsoleHelper.writeMessage(simpleDateFormat.format(date) + " - " + decimalFormat.format(count).replace(",", "."));
            total += count;
        }
        ConsoleHelper.writeMessage("Total - " + decimalFormat.format(total).replace(",", "."));
    }

    public void printCookWorkloading() {
        TreeMap<Date, TreeMap<String, Integer>> treeMap = StatisticManager.getInstance().CookWorkloading();
        for (Map.Entry<Date, TreeMap<String, Integer>> entry : treeMap.entrySet()) {
            ConsoleHelper.writeMessage(simpleDateFormat.format(entry.getKey()));

            for (Map.Entry<String, Integer> entry1 : entry.getValue().entrySet()) {
                ConsoleHelper.writeMessage(entry1.getKey() + " - " + (int) Math.ceil(((double) entry1.getValue()) / 60) + " min");
            }
            ConsoleHelper.writeMessage("");
        }
    }

    public void printActiveVideoSet() {
        TreeMap<String, Integer> mapHits = StatisticAdvertisementManager.getInstance().AdvertisementManager();
        for (Map.Entry<String, Integer> hit : mapHits.entrySet()) {
            if (hit.getValue() > 0) ConsoleHelper.writeMessage(hit.getKey() + " - " + hit.getValue());
        }
    }

    public void printArchivedVideoSet() {
        TreeMap<String, Integer> mapHits = StatisticAdvertisementManager.getInstance().AdvertisementManager();
        for (Map.Entry<String, Integer> hit : mapHits.entrySet()) {
            if (hit.getValue() == 0) ConsoleHelper.writeMessage(hit.getKey());
        }
    }


    public static class DateHolder implements Comparable<DateHolder> {
        private Date date;
        private String dateAsString;

        public DateHolder(Date date, String dateAsString) {
            this.date = date;
            this.dateAsString = dateAsString;
        }

        public Date getDate() {
            return date;
        }

        public String getDateAsString() {
            return dateAsString;
        }

        @Override
        public int compareTo(DateHolder o) {
            return date.compareTo(o.getDate());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DateHolder that = (DateHolder) o;
            return Objects.equals(date, that.date) &&
                    Objects.equals(dateAsString, that.dateAsString);
        }

        @Override
        public int hashCode() {
            return Objects.hash(date, dateAsString);
        }
    }
}
