package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArray
 *
 * @author Vladislav Nechaev
 * @since 09.04.2019
 */
public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int currentIndex;

    public SimpleArray(int length) {
        array = new Object[length];
    }

    /**
     * add
     *
     * @param model - the item to add.
     */
    public void add(T model) {
        if (array.length == currentIndex) {
            throw new IndexOutOfBoundsException("Array size exceeded.");
        }
        array[currentIndex++] = model;
    }

    /**
     * set
     *
     * @param index - index of the item to be replaced.
     * @param model - replacement item.
     */
    public void set(int index, T model) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException("Index out of array bounds.");
        }
        array[index] = model;
    }

    /**
     * remove
     *
     * @param index - index of the deleted item.
     */
    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, array.length - (index + 1));
        array[array.length - 1] = null;
    }

    /**
     * get
     *
     * @param index - index of the getting item.
     * @return - array item.
     */
    public T get(int index) {
        if (index >= array.length) {
            throw new IndexOutOfBoundsException("Index out of array bounds.");
        }
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int indx = 0;

            @Override
            public boolean hasNext() {
                return array.length > indx;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[indx++];
            }
        };
    }
}
