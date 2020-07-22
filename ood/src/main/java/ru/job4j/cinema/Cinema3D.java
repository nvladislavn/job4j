package ru.job4j.cinema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    private List<Session> sessions;

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int column, LocalDateTime date) {
        return null;
    }

    @Override
    public void add(Session session) {

    }


}
