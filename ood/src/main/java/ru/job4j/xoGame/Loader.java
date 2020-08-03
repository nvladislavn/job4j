package ru.job4j.xoGame;

import ru.job4j.xoGame.board.Board;
import ru.job4j.xoGame.board.Cell;
import ru.job4j.xoGame.io.ConsoleIn;
import ru.job4j.xoGame.io.ConsoleOut;
import ru.job4j.xoGame.io.Input;
import ru.job4j.xoGame.io.Output;
import ru.job4j.xoGame.logic.Logic;
import ru.job4j.xoGame.logic.LogicXO;
import ru.job4j.xoGame.logic.StopException;
import ru.job4j.xoGame.player.Player;
import ru.job4j.xoGame.player.PlayerInstance;

public class Loader {

    private Input in;
    private Output out;
    private Board board;
    private Logic logic;
    private Player firstPlayer;
    private Player secondPlayer;

    public Loader(Input in, Output out) {
        this.out = out;
        this.in = in;
    }

    public static void main(String[] args) {
        Loader loader = new Loader(new ConsoleIn(), new ConsoleOut());
        loader.start();
    }

    private void start() {
        initLogic();
        Player currentPlayer = firstPlayer;
        while (logic.hasGap()) {
            out.write(
                    String.format(
                            "%s%nWhat coordinate would you like to enter? Please enter coordinates separated by a SPACE:%n",
                                board.toString())
            );
            try {
                currentPlayer.play();
            } catch (IllegalArgumentException iae) {
                out.write(String.format("You entered incorrect coordinate.%n"));
                continue;
            } catch (StopException se) {
                out.write("Game is over.");
                break;
            }
            if (logic.isWin()) {
                out.write(board.toString());
                out.write(String.format("Player %s is Win!", currentPlayer.getName()));
                break;
            }
            currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
        }
    }

    private void initLogic() {
        boolean ask = true;
        while (ask) {
            try {
                out.write(
                        String.format("Please enter an odd number for the board size to start the game:%n")
                );
                int boardSize = Integer.parseInt(in.read());
                if (boardSize % 2 == 0) {
                    throw new IllegalArgumentException();
                }
                board = new Board(boardSize);
                logic = new LogicXO(board);
                firstPlayer = new PlayerInstance("Xplayer", Cell.Value.X, board, in);
                secondPlayer = new PlayerInstance("Oplayer", Cell.Value.O, board, in);
                ask = false;
            } catch (IllegalArgumentException e) {
                out.write(
                        String.format("You entered incorrect a board size. Please enter it again.%n")
                );
            }
        }
    }
}
