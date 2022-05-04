package ru.job4j.design.lsp;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingTest {

    @Test
    public void Car1Parking() {
        Parking parking = new Parking(5);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6= new Car();
        assertTrue(parking.put(car1));
        assertTrue(parking.put(car2));
        assertTrue(parking.put(car3));
        assertTrue(parking.put(car4));
        boolean res = parking.put(car5);
        assertTrue(res);
        assertFalse(parking.put(car6));
    }


    @Test
    public void Truck1Parking() {
        Parking parking = new Parking(5);
        Truck truck1 = new Truck(2);
        Truck truck2 = new Truck(3);
        Truck truck3 = new Truck(2);
        assertTrue(parking.put(truck1));
        assertTrue(parking.put(truck2));
        assertFalse(parking.put(truck3));
    }

}