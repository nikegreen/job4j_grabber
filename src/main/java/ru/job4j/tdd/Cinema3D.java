package ru.job4j.tdd;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cinema3D implements Cinema {
    private final List<Session> sessions = new ArrayList<>();
    private final List<Ticket> tickets = new ArrayList<>();

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return sessions.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        Ticket ticket = new Ticket3D(account, row, column, date);
        for (Ticket value : tickets) {
            if (value.equals(ticket)) {
                return null;
            }
        }
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public void add(Session session) {
        if (!sessions.contains(session)) {
            sessions.add(session);
        }
    }
}
