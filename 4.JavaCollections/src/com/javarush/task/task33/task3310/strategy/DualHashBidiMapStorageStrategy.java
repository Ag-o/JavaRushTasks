package com.javarush.task.task33.task3310.strategy;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap data = new DualHashBidiMap();

    @Override
    public boolean containsKey(Long key) {
        return this.data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return this.data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        this.data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        return (Long) this.data.getKey(value);
    }

    @Override
    public String getValue(Long key) {
        return (String) this.data.get(key);
    }
}
