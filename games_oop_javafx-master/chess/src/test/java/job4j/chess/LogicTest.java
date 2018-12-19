package job4j.chess;

import job4j.chess.figures.Cell;
import job4j.chess.figures.white.BishopWhite;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * LogicTest
 */
public class LogicTest {

    @Test
    public void whenSourceE2ThenDestinationC4() {
        Logic logic = new Logic();
        logic.add(new BishopWhite(Cell.E2));
        assertThat(logic.move(Cell.E2, Cell.C4), is(true));

    }
}