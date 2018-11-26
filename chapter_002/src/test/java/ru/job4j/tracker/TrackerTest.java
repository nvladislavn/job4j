package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TrackerTest
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 08.11.2018
 */
public class TrackerTest {

    Item firstItem;
    Item secondItem;
    Item thirdItem;
    Item fourthItem;

    /**
     * Tests the method add
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    /**
     * Tests the method replace from the Example
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Tests the method replace
     */
    @Test
    public void whenReplaceItemThenReturnNewItemName() {
        Tracker tracker = createTracker();
        Item replacement = new Item("replacement", "replacementDescription", 12345L);
        String indexSecond = secondItem.getId();
        replacement.setId(indexSecond);
        tracker.replace(indexSecond, replacement);
        Item[] actuallyArray = tracker.findAll();
        Item[] expectedArray = {firstItem, replacement, thirdItem, fourthItem};
        assertThat(actuallyArray, arrayContainingInAnyOrder(expectedArray)); 
    }

    /**
     * Tests the method delete
     */
    @Test
    public void whenDeleteThisItemThenReturnWithoutThatItem() {
        Tracker tracker = createTracker();
        tracker.delete(secondItem.getId());
        Item[] actuallyArray = tracker.findAll();
        Item[] expectedArray = {firstItem, thirdItem, fourthItem};
        assertThat(actuallyArray, arrayContainingInAnyOrder(expectedArray));
    }

    /**
     * Tests the method findAll.
     */
    @Test
    public void shouldBeReturnWithoutNull() {
        Tracker tracker = createTracker();
        tracker.add(null);
        Item[] actuallyArray = tracker.findAll();
        Item[] expectedArray = {firstItem, secondItem, thirdItem, fourthItem};
        assertThat(actuallyArray, arrayContainingInAnyOrder(expectedArray));
    }

    /**
     * Tests the method findByName.
     */
    @Test
    public void whenNameIsEqualThenReturn() {
        Tracker tracker = createTracker();
        fourthItem.setName("test2");
        Item[] actuallyArray = tracker.findByName("test2");
        Item[] expectedArray = {secondItem, fourthItem};
        assertThat(actuallyArray, arrayContainingInAnyOrder(expectedArray));
    }

    /**
     * Tests the method findById.
     */
    @Test
    public void whenIdIs123321TestThenSecondItem() {
        Tracker tracker = createTracker();
        secondItem.setId("123321Test");
        Item actuallyItem = tracker.findById("123321Test");
        assertThat(actuallyItem, is(secondItem));
    }

    /**
     * Service method.
     *
     * @return the instance of Tracker.
     */
    private Tracker createTracker() {
        Tracker tracker = new Tracker();
        firstItem = new Item("test1", "testDescription1", 111L);
        tracker.add(firstItem);
        secondItem = new Item("test2", "testDescription2", 222L);
        tracker.add(secondItem);
        thirdItem = new Item("test3", "testDescription3", 333L);
        tracker.add(thirdItem);
        fourthItem = new Item("test4", "testDescription4", 444L);
        tracker.add(fourthItem);
        return tracker;
    }
}