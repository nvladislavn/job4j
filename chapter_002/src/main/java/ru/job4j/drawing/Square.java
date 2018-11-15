package ru.job4j.drawing;

/**
 * Square.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 15.11.2018
 */
public class Square implements Shape {

    /**
     * draw. This method creates a string to draw a square.
     *
     * @return - a string to draw a triangle.
     */
    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder("+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n"
                                            + "+++++++\n");
        return sb.toString();
    }
}
