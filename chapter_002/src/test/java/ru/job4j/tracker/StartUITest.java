package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    private ITracker tracker;

    @Before
    public void createTracker() {
        tracker = new Tracker();
        tracker.add(new Item("Test name1", "Test description1"));
        tracker.add(new Item("Test name2", "Test description2"));
        tracker.add(new Item("Test name3", "Test description3"));
    }

    /**
     * Tests add.
     */
    @Test
    public void whenAddItemThenTrackerHasNewItem() {
        String name = "Test name";
        String desc = "Test description";
        runStartUI(new String[]{"0", name, desc, "y"});
        Item item = new Item(name, desc);
        item.setId(this.tracker.findAll().get(0).getId());
        this.tracker.add(item);
        List<Item> itemList = this.tracker.findAll();
        Item actual = itemList.get(itemList.size() - 1);
        assertThat(actual, is(item));
    }

    /**
     * Tests editItem.
     */
    @Test
    public void whenReplaceItemInArrayThenGetArrayWithNewItem() {
        List<Item> items = this.tracker.findAll();
        Item item2 = new Item("NEW name", "NEW description");
        item2.setId(this.tracker.findByName("Test name2").get(0).getId());
        List<Item> expected = Arrays.asList(items.get(0), item2, items.get(2));
        runStartUI(new String[]{"2", this.tracker.findAll().get(1).getId(),
                                        "NEW name", "NEW description",
                                        "y"});
        List<Item> actual = this.tracker.findAll();
        assertThat(actual,  is(expected));
    }

    /**
     * Tests deleteItem
     */
    @Test
    public void whenDeleteTheItemWhenReturnNull() {
        String id = this.tracker.findAll().get(1).getId();
        runStartUI(new String[]{"3", id, "y"});
        Item actual = this.tracker.findById(id);
        assertThat(actual, is((Item) null));
    }

    /**
     * runStartUI - service method to run StartUI.
     *
     * @param commands - sequence of commands.
     */
    private void runStartUI(String[] commands) {
        StubInput stubInput = new StubInput(commands);
        new StartUI(stubInput, tracker, System.out::println).init();
    }
}