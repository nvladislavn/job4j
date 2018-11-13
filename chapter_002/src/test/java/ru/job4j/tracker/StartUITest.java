package ru.job4j.tracker;

import org.junit.Test;

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

    Tracker tracker;
    StubInput stubInput;

    /**
     * Tests add.
     */
    @Test
    public void whenAddItemThenTrackerHasNewItemWithSameName() {
        tracker = new Tracker();
        runStartUI(new String[]{"0", "Test name", "Test description", "6", "y"});
        assertThat(tracker.findAll()[0].getName(), is("Test name"));
    }

    /**
     * Tests findAll.
     * В задании написано, не делать тест этого метода, но т.к. в "азарте" )) я его все-таки сделал, то решил оставить.
     */
    @Test
    public void whenFindNonNullItemsThenGetArrayOfFoundItemsNameAndDescription() {
        createTracker();
        runStartUI(new String[]{"1", "6", "y"});
        String[] expected = {"Test name1, Test description1",
                            "Test name2, Test description2",
                            "Test name3, Test description3"};
        Item[] findArray = tracker.findAll();
        String[] actual = {findArray[0].getName() + ", " + findArray[0].getDesc(),
                findArray[1].getName() + ", " + findArray[1].getDesc(),
                findArray[2].getName() + ", " + findArray[2].getDesc()};
        assertThat(actual, is(expected));
    }

    /**
     * Tests editItem.
     */
    @Test
    public void whenEnterNewNameThenGetNewName() {
        createTracker();
        runStartUI(new String[]{"2", tracker.findAll()[0].getId(),
                                "NEW name", "NEW description",
                                "6", "y"});
        String expected = "NEW name, NEW description";
        Item[] findArray = tracker.findAll();
        String actual = findArray[0].getName() + ", " + findArray[0].getDesc();
        assertThat(actual, is(expected));
    }

    /**
     * Tests deleteItem.
     */
    @Test
    public void whenDeleteTheItemWhenReturnNull() {
        createTracker();
        String id = tracker.findAll()[1].getId();
        runStartUI(new String[]{"3", id, "6", "y"});
        Item actual = tracker.findById(id);
        assertThat(actual, is((Item) null));
    }

    /**
     * runStartUI - service method to run StartUI.
     *
     * @param commands - sequence of commands.
     */
    private void runStartUI(String[] commands) {
        stubInput = new StubInput(commands);
        new StartUI(stubInput, tracker).init();
    }

    /**
     * createTracker - service method to create an instance Tracker.
     */
    private void createTracker() {
        tracker = new Tracker();
        tracker.add(new Item("Test name1", "Test description1"));
        tracker.add(new Item("Test name2", "Test description2"));
        tracker.add(new Item("Test name3", "Test description3"));
    }
}