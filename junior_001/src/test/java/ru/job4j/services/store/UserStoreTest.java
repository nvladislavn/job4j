package ru.job4j.services.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * UserStoreTest
 *
 * @author Vladislav Nechaev
 * @since 12.04.2019
 */
public class UserStoreTest {

    UserStore us;

    @Before
    public void createUserStore() {
        us = new UserStore(10);
        us.add(new User("id1"));
        us.add(new User("id2"));
        us.add(new User("id3"));
    }

    /**
     * shouldReturnId1Id2Id3
     * tests findById
     */
    @Test
    public void shouldReturnId1Id2Id3() {
        assertThat(us.findById("id1").getId(), is("id1"));
        assertThat(us.findById("id2").getId(), is("id2"));
        assertThat(us.findById("id3").getId(), is("id3"));
    }

    /**
     * shouldReturnsFirstId2ThenId4
     * tests replace
     */
    @Test
    public void shouldReturnFirstId2ThenId4() {
        assertThat(us.findById("id2").getId(), is("id2"));
        us.replace("id2", new User("id4"));
        assertThat(us.findById("id4").getId(), is("id4"));
    }

    /**
     * shouldReturnsId3
     * tests delete
     */
    @Test
    public void shouldReturnId3() {
        us.delete("id2");
        assertThat(us.findById("id3").getId(), is("id3"));
    }
}