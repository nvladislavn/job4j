package ru.job4j.services;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleStack
 *
 * @author Vladislav Nechaev
 * @since 21.04.2019
 */
public class SimpleStack<T> implements Iterable<T> {

    private SimpleArrayList<T> simpleList;
    private int modCount;

    {
        simpleList = new SimpleArrayList<>();
    }

    /**
     * push
     *
     * @param value - the item to add.
     */
    public void push(T value) {
        simpleList.add(value);
        modCount++;
    }

    /**
     * poll
     *
     * @return - the last item pushed.
     */
    public T poll() {
        modCount++;
        return simpleList.delete();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;
            private Iterator<T> it = simpleList.iterator();

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return it.hasNext();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return it.next();
            }
        };
    }
}
