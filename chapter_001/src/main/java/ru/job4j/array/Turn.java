package ru.job4j.array;

/**
 * Turn
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class Turn {

    /**
     * back method
     *
     * @param array
     * @return turned array
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }
}
