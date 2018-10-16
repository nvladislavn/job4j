package ru.job4j.loop;

/**
 * Board
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 16/10/2018
 */
public class Board {

    /**
     * paint
     *
     * @param width
     * @param height
     * @return
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                screen.append((i + j) % 2 == 0 ? 'X' : ' ');
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}