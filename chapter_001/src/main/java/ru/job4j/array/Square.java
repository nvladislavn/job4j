package ru.job4j.array;

/**
 * Square
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class Square {

    /**
     * calculate method
     *
     * @param bound
     * @return array of results
     */
    public int[] calculate(int bound) {
        int[] result = new int[bound];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) Math.pow(i + 1, 2);
        }
        return result;
    }
}
