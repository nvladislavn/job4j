package ru.job4j.tracker;

import java.util.Objects;

/**
 * Item
 *
 * @author Vladislav Nechaev
 * @version $Id$
 * @since 08.11.2018
 */
public class Item {

    private String name, desc, id;
    private long created;
    private String[] comments;

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return ("Item with name: " + name
                + ", id: " + id
                + ", description: " + desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
                return false;
    }
        Item item = (Item) o;
        return Objects.equals(name, item.name)
                && Objects.equals(desc, item.desc)
                && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, id);
    }
}
