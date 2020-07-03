package com.javarush.task.task34.task3413;

/* 
Кеш на основании SoftReference
*/

import java.util.stream.LongStream;

public class Solution {
    public static void main(String[] args) {
        SoftCache cache = new SoftCache();

        LongStream.range(0, 2_500_000).forEach(i -> cache.put(i, new AnyObject(i, null, null)));
    }
}