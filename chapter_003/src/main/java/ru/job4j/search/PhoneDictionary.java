package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * PhoneDictionary.
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 06.01.2018
 */
public class PhoneDictionary {

    private List<Person> persons = new ArrayList<>();

    /**
     * add
     * Adds person in persons
     * @param person - the instance of Person.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * find
     *
     * @param key - string search
     * @return list of found persons
     */
    public List<Person> find(String key) {
        if (key == null) {
            throw new NullPointerException("Key can't be null.");
        }
        List<Person> result = new ArrayList<>();
        for (Person person : this.persons) {
           boolean found = person.getName().contains(key)
                        || person.getSurname().contains(key)
                        || person.getAddress().contains(key)
                        || person.getPhone().contains(key);
           if (found) {
               result.add(person);
           }
        }
        return result;
    }
}
