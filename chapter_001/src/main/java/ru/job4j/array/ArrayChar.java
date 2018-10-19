package ru.job4j.array;

/**
 * ArrayChar
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 18/10/2018
 */
public class ArrayChar {

    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     *
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] prefixArray = prefix.toCharArray();
        for (int i = 0; result && i < prefixArray.length; i++) {
            if (data.length == 0) {
                break;
            }
            result = prefixArray[i] == data[i];
        }
        return result;
    }
}
