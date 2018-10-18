package ru.job4j.array;

/**
 * Check
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class Check {

    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int i = 0; i < data.length - 1; i++) {
            result = data[i] == data[i + 1];
            if (!result) {
                break;
            }
        }
        return result;
    }
}
