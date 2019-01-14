package ru.job4j.comparator;

import java.util.Comparator;

/**
 * ListCompare
 *
 * @author Vladislav Nechaev
 * @since 14.01.2019
 */
public class ListCompare implements Comparator<String> {

    /**
     * compare
     * compares two strings.
     *
     * @param left - the first string.
     * @param right - the second string.
     * @return - int.
     */
    @Override
    public int compare(String left, String right) {
        int leftLength = left.length();
        int rightLength = right.length();
        int minLength = Math.min(leftLength, rightLength);
        for (int index = 0; index < minLength; index++) {
            char l = left.charAt(index);
            char r = right.charAt(index);
            if (l != r) {
                return l - r;
            }
        }
        return leftLength - rightLength;
    }
}
