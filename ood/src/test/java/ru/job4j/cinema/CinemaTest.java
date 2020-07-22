package ru.job4j.cinema;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CinemaTest {

    private static final LocalDateTime ORDER_DATE = LocalDateTime.of(2020, 10, 10, 23, 00);
    private Account account;
    private Cinema cinema;

    @Before
    public void prepareTestData() {
        account = new AccountCinema();
        cinema = new Cinema3D();
    }

    /**
     * tests buy {@link ru.job4j.cinema.Cinema3D#buy} when no tickets.
     */
    @Test
    public void whenNoTicketsThenNull() {
        buyAllTickets();
        Ticket actual = cinema.buy(account, 1, 1, ORDER_DATE);
        assertNull(actual);
    }

    /**
     * tests buy {@link ru.job4j.cinema.Cinema3D#buy} when given negative row.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenNegativeRowThenIllegalArgumentException() {
        int negativeNumber = -1;
        Ticket actual = cinema.buy(account, negativeNumber, 1, ORDER_DATE);
    }

    /**
     * tests buy {@link ru.job4j.cinema.Cinema3D#buy} when given negative column.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenNegativeColumnThenIllegalArgumentException() {
        int negativeNumber = -1;
        Ticket actual = cinema.buy(account, 1, negativeNumber, ORDER_DATE);
    }

    /**
     * tests buy {@link ru.job4j.cinema.Cinema3D#buy} when given a date from the past.
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenOrderToThePastThenIllegalArgumentException() {
        LocalDateTime pastData = LocalDateTime.now().minusDays(1L);
        Ticket actual = cinema.buy(account, 1, 1, pastData);
    }

    @Test
    public void buy() {
        account = new AccountCinema();
        cinema = new Cinema3D();
        LocalDateTime date = LocalDateTime.of(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void find() {
        cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        MatcherAssert.assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    /**
     * Service method. Buys all tickets for the session.
     */
    private void buyAllTickets() {

    }
}
