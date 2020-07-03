package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.get(key);

        for (Map.Entry<Long, SoftReference<AnyObject>> map : cacheMap.entrySet()) {
            if (map.getKey() == key) return softReference.get();
        }
        return null;
    }

    public AnyObject put(Long key, AnyObject value) {
        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));

        //напишите тут ваш код
        for (Map.Entry<Long, SoftReference<AnyObject>> map : cacheMap.entrySet()) {
            if (map.getKey() == key) {
                if (softReference != null) {
                    AnyObject anyObject = softReference.get();
                    softReference.clear();
                    return anyObject;
                }
            }
        }
        return null;
    }

    public AnyObject remove(Long key) {
        SoftReference<AnyObject> softReference = cacheMap.remove(key);

        //напишите тут ваш код
        if (softReference != null) {
            AnyObject anyObject = softReference.get();
            softReference.clear();
            return anyObject;
        }

        return null;
    }
}