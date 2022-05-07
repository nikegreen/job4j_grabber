package ru.job4j.design.lsp;

import org.junit.Test;
import ru.job4j.design.lsp.parking.Car;
import ru.job4j.design.lsp.parking.SimpleParking;
import ru.job4j.design.lsp.parking.Truck;

import static org.junit.Assert.*;

public class SimpleParkingTest {

    @Test
    public void car1Parking() {
        SimpleParking simpleParking = new SimpleParking(5);
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        Car car4 = new Car();
        Car car5 = new Car();
        Car car6 = new Car();
        assertTrue(simpleParking.put(car1));
        assertTrue(simpleParking.put(car2));
        assertTrue(simpleParking.put(car3));
        assertTrue(simpleParking.put(car4));
        boolean res = simpleParking.put(car5);
        assertTrue(res);
        assertFalse(simpleParking.put(car6));
    }

    @Test
    public void truck1Parking() {
        SimpleParking simpleParking = new SimpleParking(5);
        Truck truck1 = new Truck(2);
        Truck truck2 = new Truck(3);
        Truck truck3 = new Truck(2);
        assertTrue(simpleParking.put(truck1));
        assertTrue(simpleParking.put(truck2));
        assertFalse(simpleParking.put(truck3));
    }
}