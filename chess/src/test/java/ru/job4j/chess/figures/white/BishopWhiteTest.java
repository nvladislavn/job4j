package ru.job4j.chess.figures.white;

import ru.job4j.chess.GameException.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * BishopWhiteTest
 */
public class BishopWhiteTest {

    /**
     * arraysShouldBeEqual
     * Tests way when the source cell and the destination cell are on the same diagonal.
     */
    @Test
    public void arraysShouldBeEqual() {
        BishopWhite bWhite = new BishopWhite(Cell.E2);
        Cell[] actual = bWhite.way(bWhite.position(), Cell.C4);
        Cell[] expected = {Cell.D3, Cell.C4};
        assertThat(actual, is(expected));
    }

    /**
     * shouldReturnsException
     * Tests way when the source cell and the destination cell are not on the same diagonal.
     */
    @Test
    public void shouldReturnsException() {
        BishopWhite bWhite = new BishopWhite(Cell.E3);
        boolean isException = false;
        try {
            Cell[] actual = bWhite.way(bWhite.position(), Cell.C4);
        } catch (ImpossibleMoveException ime) {
            isException = true;
        }
        assertThat(isException, is(true));
    }
}
