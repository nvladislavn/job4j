package ru.job4j.xoGame.player;

import ru.job4j.xoGame.board.Board;
import ru.job4j.xoGame.board.Cell;
import ru.job4j.xoGame.io.Input;
import ru.job4j.xoGame.logic.StopException;

public class PlayerInstance implements Player {

    private String name;
    private Cell.Value gameSymbol;
    private Board board;
    private Input in;

    public PlayerInstance(String name, Cell.Value gameSymbol, Board board, Input in) {
        this.name = name;
        this.gameSymbol = gameSymbol;
        this.board = board;
        this.in = in;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void play() throws IllegalArgumentException, StopException {
        String text = in.read();
        if (text.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (text.equalsIgnoreCase("stop")){
            throw new StopException();
        }
        String[] strings = text.split(" ");
        int row = Integer.parseInt(strings[0]);
        int column = Integer.parseInt(strings[1]);
        if (row < 0 || column < 0 || row >= board.size() || column >= board.size()) {
            throw new IllegalArgumentException();
        }
        if (!board.markCell(row, column, gameSymbol)) {
            throw new IllegalArgumentException();
        }
    }
}
