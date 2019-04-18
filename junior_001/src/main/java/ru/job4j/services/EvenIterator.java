package ru.job4j.services;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EvenIterator
 *
 * @author Vladislav Nechaev
 * @since 03.04.2019
 */
public class EvenIterator implements Iterator<Integer> {

    private int[] numbers;
    private int index = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * hasNext
     *
     * @return True if further there are even elements
     */
    @Override
    public boolean hasNext() {
        return Arrays
                .stream(numbers)
                .skip(index)
                .filter(i -> i % 2 == 0)
                .count() > 0;
    }

    /**
     * next
     *
     * @return the next even element
     */
    @Override
    public Integer next() {
        for (int i = index; i < numbers.length; i++) {
            int item = numbers[i];
            if (item % 2 == 0) {
                index = i + 1;
                return item;
            }
        }
        throw new NoSuchElementException();
    }
}
