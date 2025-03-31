package com.cache.hldassignment.service;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class CacheService {
    private static final int MAX_MEMORY_USAGE = 70; // Eviction at 70% usage
    private static final int MAX_CACHE_SIZE = 100_000; // Max items (adjustable)

    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public String get(String key) {
        lock.readLock().lock();
        try {
            return cache.getOrDefault(key, null);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(String key, String value) {
        lock.writeLock().lock();
        try {
            if (cache.size() >= MAX_CACHE_SIZE) {
                evictEntries();
            }
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void evictEntries() {
        int entriesToRemove = (int) (cache.size() * 0.2); // Evict 20% of entries
        cache.keySet().stream().limit(entriesToRemove).forEach(cache::remove);
    }
}
