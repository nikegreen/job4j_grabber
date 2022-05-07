package ru.job4j.design.lsp.parking;

public interface Parking {
    boolean put(Parkable car);

    boolean free(Parkable car);
}
