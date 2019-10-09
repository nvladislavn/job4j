package ru.job4j.sql.xml;

/**
 * Entry
 *
 * @author Vladislav Nechaev
 * @since 18.09.2019
 */
public class Entry {

    private int value;

    public Entry(int value) {
        this.value = value;
    }

    public Entry() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Entry with value = %d", this.value);
    }
}
