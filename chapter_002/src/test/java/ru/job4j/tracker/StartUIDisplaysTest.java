package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    Tracker tracker;
    PrintStream stdout;
    ByteArrayOutputStream baos;

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
        Item[] items = tracker.findAll();
        String expected = "-------------------\n"
                + "Program menu\n"
                + "-------------------\n"
                + "0. Add new item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find by id\n"
                + "5. Find by name\n"
                + "6. Exit the Program\n"
                + "---------------List of all applications---------------\n"
                + "Item with name: Test name1, id: " + items[0].getId() + ", description: Test description1\n"
                + "Item with name: Test name2, id: " + items[1].getId() + ", description: Test description2\n"
                + "Item with name: Test name3, id: " + items[2].getId() + ", description: Test description3\n"
                + "\n"
                + "-------------------\n"
                + "Program menu\n"
                + "-------------------\n"
                + "0. Add new item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find by id\n"
                + "5. Find by name\n"
                + "6. Exit the Program\n"
                + "The program is completed.\n";
        runStartUI(tracker, new String[]{"1", "6", "y"});
        assertThat(baos.toString(), is(expected));
    }

    /**
     * Tests findByName.
     */
    @Test
    public void whenNamesAreEqualThenReturn() {
        Item item = tracker.findByName("Test name2")[0];
        String expected = "-------------------\n"
                + "Program menu\n"
                + "-------------------\n"
                + "0. Add new item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find by id\n"
                + "5. Find by name\n"
                + "6. Exit the Program\n"
                + "---------------Search application by name---------------\n"
                + "The applications were found: \n"
                + "Item with name: Test name2, id: " + item.getId() + ", description: Test description2\n"
                + "\n"
                + "\n"
                + "-------------------\n"
                + "Program menu\n"
                + "-------------------\n"
                + "0. Add new item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find by id\n"
                + "5. Find by name\n"
                + "6. Exit the Program\n"
                + "The program is completed.\n";
        runStartUI(tracker, new String[]{"5", "Test name2", "6", "y"});
        assertThat(baos.toString(), is(expected));
    }

    /**
     * Tests findById.
     */
    @Test
    public void whenIdIsEqualThenReturn() {
        String id = tracker.findAll()[1].getId();
        String expected = "-------------------\n"
                + "Program menu\n"
                + "-------------------\n"
                + "0. Add new item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find by id\n"
                + "5. Find by name\n"
                + "6. Exit the Program\n"
                + "---------------Search application by id---------------\n"
                + "Was found the application: Item with name: Test name2, id: "
                + id
                + ", description: Test description2\n"
                + "\n"
                + "-------------------\n"
                + "Program menu\n"
                + "-------------------\n"
                + "0. Add new item\n"
                + "1. Show all items\n"
                + "2. Edit item\n"
                + "3. Delete item\n"
                + "4. Find by id\n"
                + "5. Find by name\n"
                + "6. Exit the Program\n"
                + "The program is completed.\n";
        runStartUI(tracker, new String[]{"4", id, "6", "y"});
        assertThat(baos.toString(), is(expected));
    }

    /**
     * runStartUI - service method to run StartUI.
     *
     * @param commands - sequence of commands.
     */
    private void runStartUI(Tracker tracker, String[] commands) {
        StubInput stubInput = new StubInput(commands);
        new StartUI(stubInput, tracker).init();
    }

}
