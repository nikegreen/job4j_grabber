package ru.job4j.kiss;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.List;

public class MaxMinTest {
    @Test(expected = IllegalArgumentException.class)
    public void testMaxCompNull() {
        List<Integer> list = List.of(1, 4, 5);
        MaxMin test1 = new MaxMin();
        int actual = test1.<Integer>max(list, null);
        assertEquals(5, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinCompNull() {
        List<Integer> list = List.of(1, 4, 5);
        MaxMin test1 = new MaxMin();
        int actual = test1.<Integer>min(list, null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMaxNull() {
        List<Integer> list = null;
        MaxMin test1 = new MaxMin();
        int actual = test1.<Integer>max(list, Comparator.naturalOrder());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMinNull() {
        List<Integer> list = null;
        MaxMin test1 = new MaxMin();
        int actual = test1.<Integer>min(list, Comparator.naturalOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty() throws IllegalArgumentException {
        List<Integer> list = List.of();
        MaxMin test = new MaxMin();
        int actual = test.<Integer>max(list, Comparator.naturalOrder());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testMinEmpty() {
        List<Integer> list = List.of();
        MaxMin test = new MaxMin();
        int actual = test.min(list, Comparator.naturalOrder());
    }

    @Test
    public void testMax1() {
        List<Integer> list = List.of(10);
        MaxMin test = new MaxMin();
        int actual = test.max(list, Comparator.naturalOrder());
        assertEquals(10, actual);
    }

    @Test
    public void testMin1() {
        List<Integer> list = List.of(10);
        MaxMin test = new MaxMin();
        int actual = test.min(list, Comparator.naturalOrder());
        assertEquals(10, actual);
    }

    @Test
    public void testMaxList() {
        List<Integer> list = List.of(1, 4, -2, 7, 2);
        MaxMin test = new MaxMin();
        int actual = test.max(list, Comparator.naturalOrder());
        assertEquals(7, actual);
    }

    @Test
    public void testMinList() {
        List<Integer> list = List.of(1, 4, -2, 7, 2);
        MaxMin test = new MaxMin();
        int actual = test.min(list, Comparator.naturalOrder());
        assertEquals(-2, actual);
    }
}