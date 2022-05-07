package ru.job4j.design.lsp.parking;

public class Car implements Parkable {
    public static final int SIZE = 1;

    @Override
    public int getCells() {
        return SIZE;
    }
}
