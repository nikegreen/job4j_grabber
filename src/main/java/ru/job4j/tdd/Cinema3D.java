package ru.job4j.tdd;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cinema3D implements Cinema {
    private static final int COLUMN_MAX = 30;
    private static final int ROW_MAX = 30;
    private final List<Session> sessions = new ArrayList<>();
    private final List<Ticket> tickets = new ArrayList<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return sessions.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        isInvalidSeat(row, column);
        isInvalidDate(date);
        Ticket ticket = new Ticket3D(account, row, column, date);
        for (Ticket value : tickets) {
            if (value.equals(ticket)) {
                throw new IllegalArgumentException("Билет на это место продан");
            }
        }
        tickets.add(ticket);
        return ticket;
    }

    private void isInvalidSeat(int row, int column) {
        if (row < 1 || row > ROW_MAX || column < 1 || column > COLUMN_MAX) {
            throw new IllegalArgumentException("недопустимое место");
        }
    }

    private void isInvalidDate(Calendar date) {
        if (date.compareTo(new GregorianCalendar()) < 0) {
            throw new IllegalArgumentException("недопустимая дата");
        }
    }

    @Override
    public void add(Session session) {
        if (!sessions.contains(session)) {
            sessions.add(session);
        }
    }
}
