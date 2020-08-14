package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    private SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public TreeMap<Date, Long> AdvertisementProfit() {
        List<EventDataRow> listsVideoEvent = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        Set<Date> dateSet = new HashSet<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);

        for (EventDataRow eventDataRow : listsVideoEvent) {
            dateSet.add(getParse(simpleDateFormat, eventDataRow));
        }
        TreeMap<Date, Long> mapVideo = new TreeMap<>(Comparator.reverseOrder());
        for (Date date : dateSet) {
            long amountSum = 0;
            for (EventDataRow eventDataRow : listsVideoEvent) {
                VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
                if (getParse(simpleDateFormat, eventDataRow).equals(date))
                    amountSum += videoSelectedEventDataRow.getAmount();
            }
            mapVideo.put(date, amountSum);
        }
        return mapVideo;
    }

    private Date getParse(SimpleDateFormat sdf, EventDataRow edr) {
        Date date = null;
        try {
            date = sdf.parse(sdf.format(edr.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public TreeMap<Date, TreeMap<String, Integer>> CookWorkloading() {
        List<EventDataRow> listsCookEvent = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        Set<Date> dateSet = new HashSet<>();
        Set<String> cookNames = new HashSet<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (EventDataRow eventDataRow : listsCookEvent) {
            dateSet.add(getParse(simpleDateFormat, eventDataRow));
            cookNames.add(((CookedOrderEventDataRow) eventDataRow).getCookName());
        }
        TreeMap<Date, TreeMap<String, Integer>> mapCooks = new TreeMap<>(Comparator.reverseOrder());
        for (Date date : dateSet) {
            ArrayList<CookedOrderEventDataRow> listTemp = new ArrayList<>();
            TreeMap<String, Integer> mapCookTime = new TreeMap<>();

            for (EventDataRow eventDataRow : listsCookEvent) {
                if (getParse(simpleDateFormat, eventDataRow).equals(date)) {
                    CookedOrderEventDataRow cookedOrderEventDataRow = (CookedOrderEventDataRow) eventDataRow;
                    listTemp.add(cookedOrderEventDataRow);
                }
            }

            for (String cook : cookNames) {
                int orderTime = 0;
                for (CookedOrderEventDataRow event : listTemp) {
                    if (cook.equals(event.getCookName()))
                        orderTime += event.getTime();
                }

                mapCookTime.put(cook, orderTime);
            }
            mapCooks.put(date, mapCookTime);
        }
        return mapCooks;
    }


    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }
    }
}
