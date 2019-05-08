package ru.job4j.services;

import java.util.*;

/**
 * SimpleMap
 *
 * @author Vladislav Nechaev
 * @since 04.05.2019
 */
public class SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int threshold;
    private int capacity;
    private int size;
    private NodeLinkedList<Entry<K, V>>[] array;
    private int modCount;

    public SimpleMap() {
        this(16);
    }

    public SimpleMap(int capacity) {
        array = new NodeLinkedList[capacity];
        this.capacity = capacity;
        this.threshold = (int) (capacity * LOAD_FACTOR);
    }

    /**
     * insert
     *
     * @param key   - entry key.
     * @param value - value associated with key.
     * @return - true if the insert operation was successful.
     */
    public boolean insert(K key, V value) {
        return putToBucket(indexFor(key), new Entry<>(key, value));
    }

    /**
     * putToBucket
     *
     * @param bucketIndx - index of map bucket.
     * @param newEntry   - a new create entry (key with value).
     * @return - true if the insertion into the specified bucket was successful.
     */
    private boolean putToBucket(int bucketIndx, Entry<K, V> newEntry) {
        boolean inserted = false;
        NodeLinkedList<Entry<K, V>> nList = array[bucketIndx];
        if (nList == null) {
            nList = new NodeLinkedList<>();
            array[bucketIndx] = nList;
        }
        Entry<K, V> oldEntry = findEntry(newEntry.getKey());
        if (oldEntry == null) {
            nList.add(newEntry);
            size++;
            modCount++;
            inserted = true;
            checkThreshold();
        } else {
            oldEntry.setValue(newEntry.getValue());
            inserted = true;
            modCount++;
        }
        return inserted;
    }

    /**
     * size
     *
     * @return - number of entries.
     */
    public int size() {
        return this.size;
    }

    /**
     * checkThreshold
     */
    private void checkThreshold() {
        if (size >= threshold) {
            resize();
        }
    }

    /**
     * resize
     * Resize the internal array when the threshold is exceeded.
     */
    private void resize() {
        NodeLinkedList<Entry<K, V>>[] temp = new NodeLinkedList[capacity];
        System.arraycopy(array, 0, temp, 0, capacity);
        capacity = capacity * 2;
        array = new NodeLinkedList[capacity];
        size = 0;
        threshold = (int) (capacity * LOAD_FACTOR);
        for (NodeLinkedList<Entry<K, V>> nList : temp) {
            if (nList != null && nList.size() > 0) {
                for (Entry<K, V> entry : nList) {
                    insert(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * get
     *
     * @param key - the entry key.
     * @return - the value found at the specified key.
     */
    public V get(K key) {
        Entry<K, V> entry = findEntry(key);
        return entry != null ? entry.getValue() : null;
    }

    /**
     * delete
     *
     * @param key - the entry key.
     * @return - true if the entry has been deleted.
     */
    public boolean delete(K key) {
        boolean deleted = false;
        NodeLinkedList<Entry<K, V>> nList = array[indexFor(key)];
        if (nList != null) {
            for (int i = 0; i < nList.size(); i++) {
                if (nList.get(i).getKey().equals(key)) {
                    nList.remove(i);
                    size--;
                    modCount++;
                    deleted = true;
                    break;
                }
            }
        }
        return deleted;
    }

    /**
     * findEntry
     *
     * @param key - the entry key.
     * @return - the entry found at the specified key.
     */
    private Entry<K, V> findEntry(K key) {
        NodeLinkedList<Entry<K, V>> nList = array[indexFor(key)];
        if (nList != null) {
            for (int i = 0; i < nList.size(); i++) {
                Entry<K, V> entry = nList.get(i);
                if (entry.getKey() == key || entry.getKey().equals(key)) {
                    return entry;
                }
            }
        }
        return null;
    }

    /**
     * hash
     *
     * @param key - the entry key.
     * @return - calculated hash.
     */
    private int hash(Object key) {
        int result = 0;
        if (key != null) {
            int h = key.hashCode();
            result = h ^ h >>> 16;
        }
        return result;
    }

    /**
     * indexFor
     *
     * @param - the entry key.
     * @return - bucket index.
     */
    private int indexFor(Object key) {
        return hash(key) & (array.length - 1);
    }

    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {

            private int expectedModCount = modCount;
            private int currentBucket = 0;
            private int nextIndex = 0;
            private Iterator<Entry<K, V>> subIt;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean res = false;
                NodeLinkedList<Entry<K, V>> node = array[currentBucket];
                while (node == null && currentBucket < array.length - 1) {
                    node = getNextBucket();
                }
                if (node != null && node.iterator().hasNext()) {
                    subIt = node.iterator();
                    res = true;
                }
                return res;
            }

            @Override
            public Entry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return subIt.next();
            }

            private NodeLinkedList<Entry<K, V>> getNextBucket() {
                nextIndex = 0;
                return array[++currentBucket];
            }
        };


    }

    /**
     * Entry<K, V>
     */
    private class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
