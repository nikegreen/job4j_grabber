package ru.job4j.design.lsp.parking;

public class Truck implements Parkable {
    public static final int SIZE = 1;
    private final int sizeOfTruck;

    public Truck(int sizeOfTruck) {
        if (sizeOfTruck <= SIZE) {
            throw new  IllegalArgumentException();
        }
        this.sizeOfTruck = sizeOfTruck;
    }

    @Override
    public int getCells() {
        return sizeOfTruck;
    }
}
