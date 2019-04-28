package ru.job4j.services;

import java.util.NoSuchElementException;

/**
 * SimpleQueue
 *
 * @author Vladislav Nechaev
 * @since 28.04.2019
 */
public class SimpleQueue<T> {

    private SimpleStack<T> inStack = new SimpleStack<>();
    private SimpleStack<T> outStack = new SimpleStack<>();

    /**
     * push
     *
     * @param date - an item to add.
     */
    public void push(T date) {
        inStack.push(date);
    }

    /**
     * poll
     *
     * @return - an item first in FIFO.
     */
    public T poll() {
        if (outStack.getSize() == 0) {
            fillOutStack();
        }
        return outStack.poll();
    }

    /**
     * fillOutStack
     * the method to fill the outStack.
     */
    private void fillOutStack() {
        if (inStack.getSize() == 0) {
            throw new NoSuchElementException();
        }
        int stackSize = inStack.getSize();
        for (int i = 0; i < stackSize; i++) {
            outStack.push(inStack.poll());
        }
    }
}
