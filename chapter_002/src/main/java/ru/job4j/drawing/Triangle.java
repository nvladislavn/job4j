package ru.job4j.drawing;

/**
 * Triangle.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 15.11.2018
 */
public class Triangle implements Shape {

    /**
     * draw. This method creates a string to draw a triangle.
     *
     * @return - a string to draw a triangle.
     */
    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder("   +   \n  +++  \n +++++ \n+++++++\n");
        return sb.toString();
    }
}
