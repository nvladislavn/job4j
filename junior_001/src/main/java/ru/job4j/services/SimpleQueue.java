package ru.job4j.services;

/**
 * SimpleQueue
 *
 * @author Vladislav Nechaev
 * @since 22.04.2019
 */
public class SimpleQueue<T> {

    private SimpleStack<T> inStack;
    private SimpleStack<T> outStack;
    private int inSize;
    private int outSize;

    {
        inStack = new SimpleStack<>();
        outStack = new SimpleStack<>();
    }

    /**
     * push
     *
     * @param value - the item to add.
     */
    public void push(T value) {
        inStack.push(value);
        inSize++;
    }

    /**
     * poll
     *
     * @return - an item from queue head.
     */
    public T poll() {
        if (outSize == 0 && inSize != 0) {
            fillOutStack();
        }
        outSize--;
        return outStack.poll();
    }

    /**
     * fillOutStack
     * fills outStack from inStack.
     */
    private void fillOutStack() {
        int temp = inSize;
        for (int i = 0; i < temp; i++) {
            outStack.push(inStack.poll());
            inSize--;
            outSize++;
        }
    }
}
