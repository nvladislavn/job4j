package ru.job4j.puzzle;

import ru.job4j.puzzle.firuges.Cell;
import ru.job4j.puzzle.firuges.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final int size;
    private final Figure[] figures;
    private int index = 0;

    public Logic(int size) {
        this.size = size;
        this.figures = new Figure[size * size];
    }

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.isFree(steps)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    public boolean isFree(Cell... cells) {
        boolean result = cells.length > 0;
        for (Cell cell : cells) {
            if (this.findBy(cell) != -1) {
                result = false;
                break;
            }
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * isWin method.
     *
     * Выигрышные комбинации в массиве [i][j] возможны только с индексами i = 0 || j = 0.
     *
     * @return существует ли выигрышная комбинация.
     */
    public boolean isWin() {
        int[][] table = this.convert();
        boolean result = false;
        int i = 0;
        while (!result && i < this.size) {
            int j = 0;
            while (!result && j < this.size) {
                if (table[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        if (i != 0 & j == 0) {
                            result = arrayCheck(table[i]);
                        } else if (i == 0 & j != 0) {
                            result = columnCheck(table, j);
                        } else {
                            result = arrayCheck(table[i]) || columnCheck(table, j);
                        }
                    } else {
                        break;
                    }
                }
                j++;
            }
            i++;
        }
        return result;
    }

    private boolean columnCheck(int[][] table, int columnIndex) {
        int[] array = new int[this.size];
        for (int k = 0; k < size; k++) {
            array[k] = table[k][columnIndex];
        }
        return arrayCheck(array);
    }

    private boolean arrayCheck(int[] array) {
        boolean mono = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] != array[++i]) {
                mono = false;
                break;
            }
            i--;
        }
        return mono;
    }

    public int[][] convert() {
        int[][] table = new int[this.size][this.size];
        for (int row = 0; row != table.length; row++) {
            for (int cell = 0; cell != table.length; cell++) {
                int position = this.findBy(new Cell(row, cell));
                if (position != -1 && this.figures[position].movable()) {
                    table[row][cell] = 1;
                }
            }
        }
        return table;
    }
}
