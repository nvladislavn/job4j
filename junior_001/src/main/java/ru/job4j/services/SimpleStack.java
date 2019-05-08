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

    private NodeLinkedList<T> nodeList = new NodeLinkedList<>();
    private int modCount;

    /**
     * push
     *
     * @param value - the item to add.
     */
    public void push(T value) {
        nodeList.add(value);
        modCount++;
    }

    /**
     * poll
     *
     * @return - the last item pushed.
     */
    public T poll() {
        T date = nodeList.poll();
        modCount++;
        return date;
    }

    public int getSize() {
        return nodeList.size();
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {

            private final int expectedModCount = modCount;
            private Iterator<T> it = nodeList.iterator();

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
