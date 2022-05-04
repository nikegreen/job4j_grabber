package ru.job4j.design.lsp;

public class Car implements Parkable {
    @Override
    public int getCells() {
        return 1;
    }
}
