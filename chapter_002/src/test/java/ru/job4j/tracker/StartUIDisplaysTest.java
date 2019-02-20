package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * StartUIDisplaysTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 16.11.2018
 */
public class StartUIDisplaysTest {

    private String menu = "0 : AddItem" + System.lineSeparator()
                        + "1 : ShowAll" + System.lineSeparator()
                        + "2 : EditItem" + System.lineSeparator()
                        + "3 : DeleteItem" + System.lineSeparator()
                        + "4 : FindById" + System.lineSeparator()
                        + "5 : FindByName" + System.lineSeparator();
    private Tracker tracker;
    private PrintStream stdout;
    private ByteArrayOutputStream baos;

    @Before
    public void loadInput() {
        stdout = System.out;
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    @Before
    public void createTracker() {
        tracker = new Tracker();
        tracker.add(new Item("Test name1", "Test description1"));
        tracker.add(new Item("Test name2", "Test description2"));
        tracker.add(new Item("Test name3", "Test description3"));
    }

    @After
    public void backOut() {
        System.setOut(stdout);
    }

    /**
     * Tests showAll.
     */
    @Test
    public void shouldReturnArrayNonNullItems() {
        List<Item> items = this.tracker.findAll();
        String expected = this.menu
                + "---------------List of all applications---------------" + System.lineSeparator()
                + "Item with name: Test name1, id: " + items.get(0).getId() + ", description: Test description1" + System.lineSeparator()
                + "Item with name: Test name2, id: " + items.get(1).getId() + ", description: Test description2" + System.lineSeparator()
                + "Item with name: Test name3, id: " + items.get(2).getId() + ", description: Test description3" + System.lineSeparator()
                + System.lineSeparator() + System.lineSeparator();
        runStartUI(tracker, new String[]{"1", "y"});
        assertThat(baos.toString(), is(expected));
    }

    /**
     * Tests findByName.
     */
    @Test
    public void whenNamesAreEqualThenReturn() {
        Item item = tracker.findByName("Test name2").get(0);
        String expected = menu
                + "---------------Search application by name---------------" + System.lineSeparator()
                + "The applications were found: " + System.lineSeparator()
                + "Item with name: Test name2, id: " + item.getId() + ", description: Test description2" + System.lineSeparator()
                + System.lineSeparator() + System.lineSeparator();
        runStartUI(tracker, new String[]{"5", "Test name2", "y"});
        assertThat(baos.toString(), is(expected));
    }

    /**
     * Tests findById.
     */
    @Test
    public void whenIdIsEqualThenReturn() {
        String id = tracker.findAll().get(1).getId();
        String expected = menu
                + "---------------Search application by id---------------" + System.lineSeparator()
                + "Was found the application: Item with name: Test name2, id: "
                + id
                + ", description: Test description2" + System.lineSeparator()
                + System.lineSeparator();
        runStartUI(tracker, new String[]{"4", id, "y"});
        assertThat(baos.toString(), is(expected));
    }

    /**
     * runStartUI - service method to run StartUI.
     *
     * @param commands - sequence of commands.
     */
    private void runStartUI(Tracker tracker, String[] commands) {
        StubInput stubInput = new StubInput(commands);
        new StartUI(stubInput, tracker, System.out::println).init();
    }

}
