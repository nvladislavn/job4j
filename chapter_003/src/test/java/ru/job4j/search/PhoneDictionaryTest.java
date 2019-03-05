package ru.job4j.search;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * PhoneDictionaryTest.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.01.2018
 */
public class PhoneDictionaryTest {

    /**
     * shouldReturnsJohnson
     * tests find
     */
    @Test
    public void shouldReturnsJohnson() {
        PhoneDictionary phoneDictionary = new PhoneDictionary();
        phoneDictionary.add(new Person("Adam", "Johnson", "123456789", "SAN FRANCISCO CA 94528"));
        phoneDictionary.add(new Person("Adrian", "Jones", "123456780", "SAN FRANCISCO CA 14528"));
        phoneDictionary.add(new Person("Bob", "Smith", "723456780", "LA 549235"));
        var persons = phoneDictionary.find("945");
        assertThat(persons.iterator().next().getSurname(), is("Johnson"));
    }
}