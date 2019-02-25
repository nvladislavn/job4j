package ru.job4j.travelcompany;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


/**
 * ProfilesTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 25.02.2019
 */
public class ProfilesTest {

    /**
     * tests collect
     */
    @Test
    public void shouldReturnAddressesList() {
        Address address1 = new Address("NY", "Bradway", 15, 45);
        Address address2 = new Address("Moscow", "Lenina", 50, 97);
        Address address3 = new Address("Helsinki", "Sofiankatu", 210, 158);
        List<Profile> profiles = Arrays.asList(new Profile(address1), new Profile(address2), new Profile(address3));
        assertThat(new Profiles().collect(profiles), is(Arrays.asList(address1, address2, address3)));
    }

}