package ru.job4j.services;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DynamicList
 *
 * @author Vladislav Nechaev
 * @since 20.04.2019
 */
public class DynamicList<E> implements Iterable<E> {

    private Object[] container;
    private int boundIndex;
    private int modCount;

    public DynamicList(int size) {
        container = new Object[size];
    }

    /**
     * add
     *
     * @param value - the item to add.
     */
    public void add(E value) {
        if (boundIndex == container.length) {
            Object[] temp = new Object[container.length + 1];
            System.arraycopy(container, 0, temp, 0, container.length);
            container = temp;
            modCount++;
        }
        container[boundIndex++] = value;
    }

    /**
     * get
     *
     * @param index - an index of the getting item.
     * @return - the specified item.
     */
    public E get(int index) {
        if (index >= boundIndex) {
            throw new NoSuchElementException();
        }
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentIndex < boundIndex;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[currentIndex++];
            }
        };
    }
}
