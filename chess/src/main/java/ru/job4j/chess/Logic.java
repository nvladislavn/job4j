package ru.job4j.chess;

import ru.job4j.chess.GameException.FigureNotFoundException;
import ru.job4j.chess.GameException.ImpossibleMoveException;
import ru.job4j.chess.GameException.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException,
                                                    OccupiedWayException,
                                                    FigureNotFoundException {
        try {
            int item = findByCell(source);
            if (item == -1) {
                throw new FigureNotFoundException();
            }
            Figure figure = this.figures[item];
            if (!isPossibleWay(figure.way(source, dest))) {
                throw new OccupiedWayException("The way is occupied!");
            }
            this.figures[item] = figure.copy(dest);
            return true;
        } catch (RuntimeException re) {
            System.out.println(re.getMessage());
            return false;
        }
    }

    private boolean isPossibleWay(Cell[] way) {
        boolean isPossible = true;
        int count = 0;
        while (isPossible && count < way.length) {
            for (Figure figure : this.figures) {
                if (figure != null && figure.position().equals(way[count])) {
                    isPossible = false;
                    break;
                }
            }
            count++;
        }
        return isPossible;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * findByCell
     *
     * @param source - cell
     * @return - position of the figure in the figures.
     */
    private int findByCell(Cell source) {
        int res = -1;
        for (int i = 0; i < this.figures.length; i++) {
            if (this.figures[i] != null && this.figures[i].position().equals(source)) {
                res = i;
                break;
            }
        }
        return res;
    }
}
