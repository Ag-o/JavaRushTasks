package com.javarush.task.task27.task2712.ad;

import java.util.List;
import java.util.TreeMap;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public TreeMap<String, Integer> AdvertisementManager() {
        List<Advertisement> list = advertisementStorage.getInstance().list();
        TreeMap<String, Integer> mapHits = new TreeMap<>(String::compareToIgnoreCase);

        list.forEach(adv -> mapHits.put(adv.getName(), adv.getHits()));

        return mapHits;
    }
}
