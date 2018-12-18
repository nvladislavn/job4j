package ru.job4j.chess;

import org.junit.Before;
import ru.job4j.chess.GameException.OccupiedWayException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.black.PawnBlack;
import ru.job4j.chess.figures.white.BishopWhite;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * LogicTest
 */
public class LogicTest {

    private Logic logic;

    /**
     * The first filling.
     */
    @Before
    public void createAndFill() {
        this.logic = new Logic();
        this.logic.add(new BishopWhite(Cell.E2));
    }

    /**
     * Tests move
     */
    @Test
    public void whenSourceE2ThenDestinationC4() {
        assertThat(this.logic.move(Cell.E2, Cell.C4), is(true));
    }

    /**
     * Tests move
     */
    @Test
    public void shouldBeOccupiedWayException() {
        boolean isException = false;
        this.logic.add(new PawnBlack(Cell.D3));
        try {
            this.logic.move(Cell.E2, Cell.C4);
        } catch (OccupiedWayException owe) {
            isException = true;
        }
        assertThat(isException, is(true));
    }


}