package job4j.chess.figures.white;

import job4j.chess.GameException.ImpossibleMoveException;
import job4j.chess.figures.Cell;
import job4j.chess.figures.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopWhite implements Figure {
    private final Cell position;

    public BishopWhite(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("A Bishop can move only diagonally!");
        }
        int deltaX = dest.x - source.x;
        int deltaY = dest.y - source.y;
        steps = new Cell[Math.abs(deltaX)];
        for (int i = 1; i <= Math.abs(deltaX); i++) {
            int tempX = source.x + (deltaX > 0 ? i : -i);
            int tempY = source.y + (deltaY > 0 ? i : -i);
            Cell step = findByXY(tempX, tempY);
            if (step == null) {
                throw new ImpossibleMoveException();
            }
            steps[i-1] = step;
        }
        return steps;
    }

    private Cell findByXY(int x, int y) {
        Cell res = null;
        for (Cell cell : Cell.values()) {
            if (cell.x == x && cell.y == y) {
                res =cell;
            }
        }
        return res;
    }

    private boolean isDiagonal(Cell source, Cell dest) {
        boolean res = false;
        if (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y)) {
            res = true;
        }
        return res;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopWhite(dest);
    }
}
