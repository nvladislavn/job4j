package ru.job4j.services;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * NodeLinkedList
 *
 * @author Vladislav Nechaev
 * @since 21.04.2019
 */
public class NodeLinkedList<E> implements Iterable<E> {

    private SimpleArrayList<E> simpleList;
    private int modCount;

    {
        simpleList = new SimpleArrayList<>();
    }

    /**
     * add
     *
     * @param value - the item to add.
     */
    public void add(E value) {
        simpleList.add(value);
        modCount++;
    }

    /**
     * get
     *
     * @param index - an index of getting item.
     * @return - the specified item.
     */
    public E get(int index) {
        return simpleList.get(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;
            private Iterator<E> it = simpleList.iterator();

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return it.hasNext();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return it.next();
            }
        };
    }
}
