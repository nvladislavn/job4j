package ru.job4j.drawing;

/**
 * Paint.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 15.11.2018
 */
public class Paint {

    /**
     * draw. This method displays the given figures.
     * @param - figure to draw.
     */
    public void draw(Shape s) {
        System.out.print(s.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        paint.draw(new Square());
    }
}
