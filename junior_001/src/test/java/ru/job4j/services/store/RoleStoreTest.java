package ru.job4j.services.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * RoleStoreTest
 *
 * @author Vladislav Nechaev
 * @since 12.04.2019
 */
public class RoleStoreTest {

    RoleStore rs;

    @Before
    public void createUserStore() {
        rs = new RoleStore(10);
        rs.add(new Role("id1"));
        rs.add(new Role("id2"));
        rs.add(new Role("id3"));
    }

    /**
     * shouldReturnId1Id2Id3
     * tests findById
     */
    @Test
    public void shouldReturnId1Id2Id3() {
        assertThat(rs.findById("id1").getId(), is("id1"));
        assertThat(rs.findById("id2").getId(), is("id2"));
        assertThat(rs.findById("id3").getId(), is("id3"));
    }

    /**
     * shouldReturnsFirstId2ThenId4
     * tests replace
     */
    @Test
    public void shouldReturnFirstId2ThenId4() {
        assertThat(rs.findById("id2").getId(), is("id2"));
        rs.replace("id2", new Role("id4"));
        assertThat(rs.findById("id4").getId(), is("id4"));
    }

    /**
     * shouldReturnsId3
     * tests delete
     */
    @Test
    public void shouldReturnId3() {
        rs.delete("id2");
        assertThat(rs.findById("id3").getId(), is("id3"));
    }
}