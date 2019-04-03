package ru.job4j.services;

import java.util.Iterator;

/**
 * MatrixIterator
 *
 * @author Vladislav Nechaev
 * @since 03.04.2019
 */
public class MatrixIterator implements Iterator<Integer> {

    private int[][] array;
    private int firstIndex;
    private int secondIndex;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    /**
     * hasNext
     *
     * @return True if has the next element
     */
    @Override
    public boolean hasNext() {
        return array.length - 1 > firstIndex
                || array[firstIndex].length > secondIndex;
    }

    /**
     * next
     *
     * @return the next element
     */
    @Override
    public Integer next() {
        if (secondIndex == array[firstIndex].length) {
            return array[++firstIndex][0];
        } else {
            return array[firstIndex][secondIndex++];
        }
    }
}
