package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever {
    OriginalRetriever originalRetriever;
    LRUCache<Long, Object> cache = new LRUCache(20);

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object o = cache.find(id);
        if (o == null) {
            o = originalRetriever.retrieve(id);
            cache.set(id, o);
            return cache.find(id);
        } else {
            return o;
        }
    }
}
