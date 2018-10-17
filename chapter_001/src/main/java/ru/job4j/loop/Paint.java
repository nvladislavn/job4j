package ru.job4j.loop;

/**
 * Paint
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 17/10/2018
 */
public class Paint {

    /**
     * pyramid
     *
     * @param height
     * @return String
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int width = 2 * height - 1;
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
