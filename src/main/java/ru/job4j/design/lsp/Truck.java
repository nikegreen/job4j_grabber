package ru.job4j.design.lsp;

public class Truck implements Parkable {
    private final int size;

    public Truck(int size) {
        if (size < 1) {
            size = 1;
        }
        this.size = size;
    }

    @Override
    public int getCells() {
        return size;
    }
}
