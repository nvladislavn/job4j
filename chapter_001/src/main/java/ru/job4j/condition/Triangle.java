package ru.job4j.condition;

/**
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 15/10/2018
 */
public class Triangle {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * @param ab расстояние между точками a b
     * @param ac расстояние между точками a c
     * @param bc расстояние между точками b c
     * @return Периметр.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Вычисляет площадь треугольника
     *
     * @return Площадь, если треугольник существует или -1, если треугольника нет.
     */
    public double area() {
        double result = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double per = this.period(ab, ac, bc); //эту строку кода, по-моему, лучше перенести в блок if,
        //чтобы не делать лишних вычислений, если проверка на существование треугольника не пройдет.
        if (this.exist(ab, ac, bc)) {
            result = Math.sqrt(per * (per - ab) * (per - ac) * (per - bc));
        }
        return result;
    }

    /**
     * Проверка на существование треугольника
     *
     * @param ab
     * @param ac
     * @param bc
     * @return Существует
     *
     * Также необходимо бы было проверить не находятся ли точки на одной прямой,
     * но исходя из постановки задачи этого сделать нелья, т.к. кроме длины отрезков надо
     * передать в качестве входящих параметров и координаты точек, у которых установлен
     * модификатор доступа private по условию задания, и здесь получить мы их не можем.
     */
    private boolean exist(double ab, double ac, double bc) {
        return ab > 0 && ac > 0 && bc > 0 && (ab + ac > bc) && (ab + bc > ac) && (ac + bc > ab);
    }
}
