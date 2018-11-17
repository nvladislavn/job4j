package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * StartUITest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 12.11.2018
 */
public class StartUITest {

    /**
     * Tests add.
     */
    @Test
    public void whenAddItemThenTrackerHasNewItem() {
        Tracker tracker = new Tracker();
        String name = "Test name";
        String desc = "Test description";
        runStartUI(tracker, new String[]{"0", name, desc, "6", "y"});
        Item item = new Item(name, desc);
        item.setId(tracker.findAll()[0].getId());
        Item[] expected = {item};
        Item[] actual = tracker.findAll();
        assertThat(actual, is(expected));
    }

    /**
     * Tests editItem.
     */
    @Test
    public void whenReplaceItemInArrayThenGetArrayWithNewItem() {
        Tracker tracker = createTracker();
        Item[] items = tracker.findAll();
        Item item2 = new Item("NEW name", "NEW description");
        item2.setId(tracker.findByName("Test name2")[0].getId());
        Item[] expected = {items[0], item2, items[2]};
        runStartUI(tracker, new String[]{"2", tracker.findAll()[1].getId(),
                                        "NEW name", "NEW description",
                                        "6", "y"});
        Item[] actual = tracker.findAll();
        assertThat(actual, is(expected));
    }

    /**
     * Tests deleteItem v.1.
     */
    @Test
    public void whenDeleteTheItemWhenReturnNull() {
        Tracker tracker = createTracker();
        String id = tracker.findAll()[1].getId();
        runStartUI(tracker, new String[]{"3", id, "6", "y"});
        Item actual = tracker.findById(id);
        assertThat(actual, is((Item) null));
    }

    /**
     * Tests deleteItem v.2.
     */
    @Test
    public void whenDeleteTheItemWhenReturnArrayWithoutItem() {
        Tracker tracker = createTracker();
        Item[] items = tracker.findAll();
        Item[] expected = Arrays.copyOf(items, items.length - 1);
        String id = items[2].getId();
        runStartUI(tracker, new String[]{"3", id, "6", "y"});
        Item[] actual = tracker.findAll();
        assertThat(actual, is(expected));
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

    /**
     * createTracker - service method to create an instance Tracker.
     */
    private Tracker createTracker() {
        Tracker tracker = new Tracker();
        tracker.add(new Item("Test name1", "Test description1"));
        tracker.add(new Item("Test name2", "Test description2"));
        tracker.add(new Item("Test name3", "Test description3"));
        return tracker;
    }
}