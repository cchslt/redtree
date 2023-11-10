package com.stone.leecode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chen
 * @create 2022-01-20 10:56
 **/

public class LRUCache<K,V> extends LinkedHashMap<K, V> {
    private final int CACHE_SIZE;


    public LRUCache(int CACHE_SIZE) {
        super((int)Math.ceil(CACHE_SIZE / 0.75) + 1, 0.75f, true);
        this.CACHE_SIZE = CACHE_SIZE;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }

    public static void main(String[] args) {
        LRUCache lruCache1 = new LRUCache(2);
        lruCache1.put("a", "a1");
        printMap(lruCache1);
        lruCache1.put("a2", "a2");
        printMap(lruCache1);
        lruCache1.put("a3", "a3");
        printMap(lruCache1);


    }

    private static void printMap(LRUCache lruCache) {
        lruCache.forEach((k, v) -> {
            System.out.println("key:" + k + "   value:" + v);
        });
    }
}
