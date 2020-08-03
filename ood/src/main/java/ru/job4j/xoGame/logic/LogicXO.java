package ru.job4j.xoGame.logic;

import ru.job4j.xoGame.board.Board;
import ru.job4j.xoGame.board.Cell;

public class LogicXO implements Logic {

    private Board board;

    public LogicXO(Board board) {
        this.board = board;
    }

    @Override
    public boolean isWin() {
        return isWinRow() || isWinColumn() || isWinDiagonal();
    }

    private boolean isWinRow() {
        boolean res = false;
        for (int i = 0; i < board.size(); i++) {
            Cell.Value firstCellInRow = board.getCell(i, 0).value();
            if (firstCellInRow == Cell.Value.EMPTY) {
                continue;
            }
            res = true;
            for (int j = 1; j < board.size(); j++) {
                res = res & board.getCell(i, j).value() == firstCellInRow;
            }
            if (res) {
                break;
            }
        }
        return res;
    }

    private boolean isWinColumn() {
        boolean res = false;
        for (int i = 0; i < board.size(); i++) {
            Cell.Value firstCellInColumn = board.getCell(0, i).value();
            if (firstCellInColumn == Cell.Value.EMPTY) {
                continue;
            }
            res = true;
            for (int j = 0; j < board.size(); j++) {
                res = res & board.getCell(j, i).value() == firstCellInColumn;
            }
            if (res) {
                break;
            }
        }
        return res;
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
