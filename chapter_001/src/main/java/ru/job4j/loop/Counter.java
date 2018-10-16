package ru.job4j.loop;

/**
 * Counter
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 16/10/2018
 */
public class Counter {

    public int add(int start, int finish) {
        int summ = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                summ += i;
            }
        }
        return summ;
    }
}
