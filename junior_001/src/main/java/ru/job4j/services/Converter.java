package ru.job4j.services;

import java.util.*;

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
            private List<Iterator<Integer>> iterators = new ArrayList<>();
            private Iterator<Iterator<Integer>> iterator;

            {
                while (it.hasNext()) {
                    iterators.add(it.next());
                }
                iterator = iterators.iterator();
            }

            private void setCurrentIterator() {
                if (currentIterator == null && iterator.hasNext()) {
                    currentIterator = iterator.next();
                } else if (!currentIterator.hasNext() && iterator.hasNext()) {
                    currentIterator = iterator.next();
                }
            }

            @Override
            public boolean hasNext() {
                setCurrentIterator();
                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                setCurrentIterator();
                if (!hasNext()) {
                    throw new NoSuchElementException("No return elements");
                }
                return currentIterator.next();
            }
        };
    }
}
