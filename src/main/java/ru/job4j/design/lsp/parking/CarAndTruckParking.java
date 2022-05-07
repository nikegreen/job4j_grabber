package ru.job4j.design.lsp.parking;

public class CarAndTruckParking implements Parking {
    private final SimpleParking carParking;
    private final SimpleParking truckParking;

    public CarAndTruckParking(int carSize, int truckSize) {
        carParking = new SimpleParking(carSize);
        truckParking = new SimpleParking(truckSize * 2);
    }

    @Override
    public boolean put(Parkable car) {
        return car.getCells() > 1
                ? truckParking.put(car) || carParking.put(car)
                : carParking.put(car);
    }

    @Override
    public boolean free(Parkable car) {
        return carParking.free(car) || truckParking.free(car);
    }
}
