package ru.job4j.array;

/**
 * FindLoop
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class FindLoop {

    /**
     * indexOf method
     *
     * @param data
     * @param el
     * @return index of the first found array element
     */
    public int indexOf(int[] data, int el) {
        int result = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == el) {
                result = i;
                break;
            }
        }
        return result;
    }
}
