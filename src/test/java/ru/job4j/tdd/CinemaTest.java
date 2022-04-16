package ru.job4j.tdd;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    /**
     * пробуем купить билет 2-жды
     */
    @Test
    public void whenBuy2Equal() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
        ticket = cinema.buy(account, 1, 1, date);
        assertNull(ticket);
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(sessionTemp -> true);
        assertThat(sessions, is(Arrays.asList(session)));
    }

    /**
     * поиск для одной вбранной сессии
     */
    @Test
    public void whenFind2() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Session session2 = new Session3D();
        cinema.add(session2);
        List<Session> sessions = cinema.find(session -> session2.equals(session));
        assertThat(sessions, is(Arrays.asList(session2)));
    }

    @Test
    public void whenAdd() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(session1 -> true);
        assertThat(sessions, is(Arrays.asList(session)));
    }

    /**
     * добавляем 2 разных сеансов
     */
    @Test
    public void whenAdd2() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        Session session2 = new Session3D();
        cinema.add(session);
        cinema.add(session2);
        List<Session> sessions = cinema.find(session1 -> true);
        assertThat(sessions, is(Arrays.asList(session, session2)));
    }

    /**
     * добавляем 2 одинаковых сеансов
     */
    @Test
    public void whenAdd2Equal() {
        Cinema cinema = new Cinema3D();
        Session session2 = new Session3D();
        cinema.add(session2);
        cinema.add(session2);
        List<Session> sessions = cinema.find(session1 -> true);
        assertThat(sessions, is(Arrays.asList(session2)));
    }
}