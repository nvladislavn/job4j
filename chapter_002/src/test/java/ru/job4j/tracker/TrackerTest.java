package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        ITracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * Tests the method replace from the Example
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        ITracker tracker = new Tracker();
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
        ITracker tracker = createTracker();
        Item replacement = new Item("replacement", "replacementDescription", 12345L);
        String indexSecond = secondItem.getId();
        replacement.setId(indexSecond);
        tracker.replace(indexSecond, replacement);
        List<Item> actually = tracker.findAll();
        List<Item> expected = Arrays.asList(firstItem, replacement, thirdItem, fourthItem);
        assertThat(actually, is(expected));
    }

    /**
     * Tests the method delete
     */
    @Test
    public void whenDeleteThisItemThenReturnWithoutThatItem() {
        ITracker tracker = createTracker();
        tracker.delete(secondItem.getId());
        List<Item> actually = tracker.findAll();
        List<Item>  expected = Arrays.asList(firstItem, thirdItem, fourthItem);
        assertThat(actually, is(expected));
    }

    /**
     * Tests the method findAll.
     */
    @Test
    public void shouldBeReturnWithoutNull() {
        ITracker tracker = createTracker();
        tracker.add(null);
        List<Item> actually = tracker.findAll();
        List<Item> expected = Arrays.asList(firstItem, secondItem, thirdItem, fourthItem);
        assertThat(actually, is(expected));
    }

    /**
     * Tests the method findByName.
     */
    @Test
    public void whenNameIsEqualThenReturn() {
        ITracker tracker = createTracker();
        fourthItem.setName("test2");
        List<Item> actually = tracker.findByName("test2");
        List<Item> expected = Arrays.asList(secondItem, fourthItem);
        assertThat(actually, is(expected));
    }

    /**
     * Tests the method findById.
     */
    @Test
    public void whenIdIs123321TestThenSecondItem() {
        ITracker tracker = createTracker();
        secondItem.setId("123321Test");
        Item actuallyItem = tracker.findById("123321Test");
        assertThat(actuallyItem, is(secondItem));
    }

    /**
     * Service method.
     *
     * @return the instance of Tracker.
     */
    private ITracker createTracker() {
        ITracker tracker = new Tracker();
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