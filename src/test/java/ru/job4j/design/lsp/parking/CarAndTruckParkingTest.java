package ru.job4j.design.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarAndTruckParkingTest {
    @Test
    public void car1Parking() {
        CarAndTruckParking simpleParking = new CarAndTruckParking(5, 2);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();
        Truck truck1 = new Truck(2);
        Truck truck2 = new Truck(2);
        Truck truck3 = new Truck(2);
        assertTrue(simpleParking.put(car1));
        assertTrue(simpleParking.put(car2));
        assertTrue(simpleParking.put(car3));
        assertTrue(simpleParking.put(car4));
        assertTrue(simpleParking.put(car5));
        assertFalse(simpleParking.put(car6));
        assertTrue(simpleParking.put(truck1));
        assertTrue(simpleParking.put(truck2));
        assertFalse(simpleParking.put(truck3));
    }

    @Test
    public void truck1Parking() {
        CarAndTruckParking simpleParking = new CarAndTruckParking(5, 2);
        Car car1 = new Car();
        Car car2 = new Car();
        Truck truck1 = new Truck(2);
        Truck truck2 = new Truck(2);
        Truck truck3 = new Truck(2);
        Truck truck4 = new Truck(2);
        Truck truck5 = new Truck(2);
        assertTrue(simpleParking.put(car1));
        assertTrue(simpleParking.put(truck1));
        assertTrue(simpleParking.put(truck2));
        assertTrue(simpleParking.put(truck3));
        assertTrue(simpleParking.put(truck4));
        assertFalse(simpleParking.put(car2));
        assertFalse(simpleParking.put(truck5));
    }

}