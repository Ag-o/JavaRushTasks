package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> longStringHashMap = new HashMap<>();
    private HashMap<String, Long> stringLongHashMap = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return this.longStringHashMap.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return this.stringLongHashMap.containsKey(value);
    }

    @Override
    public void put(Long key, String value) {
        this.longStringHashMap.put(key, value);
        this.stringLongHashMap.put(value, key);
    }

    @Override
    public Long getKey(String value) {
        return this.stringLongHashMap.get(value);
    }

    @Override
    public String getValue(Long key) {
        return this.longStringHashMap.get(key);
    }
}
