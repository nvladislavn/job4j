package ru.job4j.services;

import java.util.*;
import java.util.stream.Stream;

/**
 * Converter
 *
 * @author Vladislav Nechaev
 * @since 08.04.2019
 */
public class Converter {

    /**
     * convert
     *
     * @param it - an iterator of iterators.
     * @return - an integer iterator.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<>() {

            private Iterator<Integer> currentIterator;

            private void setCurrentIterator() {
                if (currentIterator == null && it.hasNext()) {
                    currentIterator = it.next();
                }
                boolean ind = true;
                while (ind) {
                    if (!currentIterator.hasNext() && it.hasNext()) {
                        currentIterator = it.next();
                    } else {
                        ind = false;
                    }

                }
            }

            @Override
            public boolean hasNext() {
                setCurrentIterator();
                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No return elements");
                }
                return currentIterator.next();
            }
        };
    }
}
