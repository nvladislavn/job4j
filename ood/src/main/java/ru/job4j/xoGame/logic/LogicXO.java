package ru.job4j.xoGame.logic;

import ru.job4j.xoGame.board.Board;
import ru.job4j.xoGame.board.Cell;

import java.util.stream.IntStream;

public class LogicXO implements Logic {

    private Board board;

    public LogicXO(Board board) {
        this.board = board;
    }

    @Override
    public boolean isWin() {
        return IntStream
                .range(0, board.size())
                .anyMatch(i -> isWinRow(i) || isWinColumn(i)) || isWinDiagonal();
    }

    private boolean isWinRow(int i) {
        Cell.Value firstValue = board.getCell(i, 0).value();
        if (firstValue == Cell.Value.EMPTY) {
            return false;
        }
        for (int j = 1; j < board.size(); j++) {
            if (board.getCell(i, j).value() != firstValue) {
                return false;
            }
        }
        return true;
    }

    private boolean isWinColumn(int i) {
        Cell.Value firstValue = board.getCell(0, i).value();
        if (firstValue == Cell.Value.EMPTY) {
            return false;
        }
        for (int j = 1; j < board.size(); j++) {
            if (board.getCell(j, i).value() != firstValue) {
                return false;
            }
        }
        return true;
    }

    private boolean isWinDiagonal() {
        boolean leftRes = true;
        boolean rightRes = true;
        Cell.Value topLeft = board.getCell(0, 0).value();
        if (topLeft == Cell.Value.EMPTY) {
            leftRes = false;
        }
        Cell.Value topRight = board.getCell(0, board.size() - 1).value();
        if (topRight == Cell.Value.EMPTY) {
            rightRes = false;
        }
        if (leftRes || rightRes) {
            for (int i = 0; i < board.size(); i++) {
                leftRes = leftRes & board.getCell(i, i).value() == topLeft;
                rightRes = rightRes & board.getCell(board.size() - 1 - i, i).value() == topRight;
            }
        }
        return leftRes || rightRes;
    }

    @Override
    public boolean hasGap() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.getCell(i, j).isBlank()) {
                    return true;
                }
            }
        }
        return false;
    }
}
