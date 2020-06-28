package com.javarush.task.task33.task3310.strategy;

import com.google.common.collect.HashBiMap;

public class HashBiMapStorageStrategy implements StorageStrategy {
    private HashBiMap hashBiMap = HashBiMap.create();

    @Override
    public boolean containsKey(Long key) {
        return this.hashBiMap.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return this.hashBiMap.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        this.hashBiMap.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        return (Long) this.hashBiMap.inverse().get(value);
    }

    @Override
    public String getValue(Long key) {
        return (String) this.hashBiMap.get(key);
    }

}
