package ru.job4j.max;

/**
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 15/10/2018
 */
public class Max {

    /**
     * Maximum of two numbers
     * @param first
     * @param second
     * @return maximum of two numbers
     */
    public int max(int first, int second) {
            return first > second ? first : second;
    }

    /**
     * Maximum of three numbers
     * @param first
     * @param second
     * @param third
     * @return maximum of three numbers
     */
    public int max(int first, int second, int third) {
        return this.max(max(first, second), third);
    }
}
