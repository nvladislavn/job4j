package ru.job4j.cinema;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, LocalDateTime date);

    void add(Session session);
}
