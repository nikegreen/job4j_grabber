package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V strongReference = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (strongReference == null) {
            strongReference = load(key);
            put(key, strongReference);
        }
        return strongReference;
    }

    protected abstract V load(K key);

    public Map<K, SoftReference<V>> getCache() {
        return new HashMap<>(cache);
    }
}
