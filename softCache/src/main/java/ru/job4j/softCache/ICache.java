package ru.job4j.softCache;

import java.io.IOException;

/**
 * SoftCache
 *
 * @author Vladislav Nechaev
 * @since 06.05.2020
 */
public interface ICache<K, V> {

    /**
     * add
     * Adds a key-value pair to the cache.
     *
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * getValue
     * returns the key value from the cache.
     *
     * @param key
     * @return
     * @throws IOException
     */
    V getValue(K key) throws IOException;
}
