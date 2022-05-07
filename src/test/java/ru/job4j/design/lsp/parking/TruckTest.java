package ru.job4j.design.lsp.parking;

import org.junit.Test;

import static org.junit.Assert.*;

public class TruckTest {
    @Test(expected = IllegalArgumentException.class)
    public void testException1() {
        Truck truck = new Truck(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testException0() {
        Truck truck = new Truck(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionMinus1() {
        Truck truck = new Truck(-1);
    }

    @Test
    public void test1() {
        Truck truck = new Truck(2);
        assertEquals(truck.getCells(), 2);
    }
}