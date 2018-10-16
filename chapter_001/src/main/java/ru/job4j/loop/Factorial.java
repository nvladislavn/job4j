package ru.job4j.loop;

/**
 * Factorial
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 16/10/2018
 */
public class Factorial {

    /**
     * Calculations the factorial of a given number
     *
     * @param n
     * @return factorial calculation result
     */
    public int calc(int n) {
        int product = 1;
        int count = 1;
        do {
            product *= count;
            count++;
        } while (count <= n);
        return product;
    }
}
