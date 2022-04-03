package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    public V get(K key) {
        SoftReference<V> softReference = cache.get(key);
        V strongReference = null;
        if (softReference != null) {
            strongReference = softReference.get();
            if (strongReference == null) {
                put(key, load(key));
                strongReference = get(key);
            }
        }
        return strongReference;
    }

    protected abstract V load(K key);
}
