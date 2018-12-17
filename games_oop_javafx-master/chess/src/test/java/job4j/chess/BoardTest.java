package job4j.chess;

import job4j.chess.figures.Cell;
import job4j.chess.figures.white.BishopWhite;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void whenSourceE2ThenDestinationC4() {
        Board board = new Board();
        board.add(new BishopWhite(Cell.E2));
        assertThat(board.move(Cell.E2, Cell.C4), is(true));

    }
}