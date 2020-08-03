package ru.job4j.xoGame.board;

public class Cell {

    private int rowPosition;
    private int colPosition;
    private Value value;

    public Cell(int rowPosition, int colPosition) {
        this.rowPosition = rowPosition;
        this.colPosition = colPosition;
        this.value = Value.EMPTY;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    public Value value() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public boolean isBlank() {
        return this.value == Value.EMPTY;
    }

    public enum Value {
        X("x"),
        O("o"),
        EMPTY("+");

        String symbol;

        Value(String symbol) {
            this.symbol = symbol;
        }
    }
}
