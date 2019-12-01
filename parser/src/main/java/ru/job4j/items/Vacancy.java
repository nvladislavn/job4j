package ru.job4j.items;

import ru.job4j.Item;

import java.util.Date;
import java.util.Objects;

/**
 * ru.job4j.items.Vacancy
 *
 * @author Vladislav Nechaev
 * @since 10.11.2019
 */
public class Vacancy implements Item {

    private String name;
    private String url;
    private String text;
    private Date msgDate;

    public Vacancy(String name, String url, String text, Date msgDate) {
        this.name = name;
        this.url = url;
        this.text = text;
        this.msgDate = msgDate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Date getMsgDate() {
        return msgDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return name.equals(vacancy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
