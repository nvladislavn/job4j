package ru.job4j.xoGame.board;

import java.util.StringJoiner;

public class Board {

    private static final int DEFAULT_SIZE = 3;
    private static final String DELIMETER = "-";
    private int size;
    private Cell[][] field;

    public Board(int dimension) {
        this.size = dimension;
        field = new Cell[dimension][dimension];
        fillField();
    }

    public Board() {
        this(DEFAULT_SIZE);
    }

    private void fillField() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    public boolean markCell(int row, int column, Cell.Value value) {
        boolean res;
        if (field[row][column].isBlank()) {
            field[row][column].setValue(value);
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringJoiner fieldString = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < size; i++) {
            StringJoiner row = new StringJoiner(DELIMETER);
            for (int j = 0; j < size; j++) {
                row.add(field[i][j].value().symbol);
            }
            fieldString.add(row.toString());
        }
        return fieldString.toString();
    }
}
