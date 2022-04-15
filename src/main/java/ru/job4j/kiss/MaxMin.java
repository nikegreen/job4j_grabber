package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class MaxMin {
    private <T> T find(List<T> value, Comparator<T> comparator) {
        ListIterator<T> iterator = value.listIterator();
        T tempMaxMin = iterator.next();
        while (iterator.hasNext()) {
            T tmp = iterator.next();
            if (comparator.compare(tmp, tempMaxMin) > 0) {
                tempMaxMin = tmp;
            }
        }
        return tempMaxMin;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        validate(value, comparator);
        return find(value, comparator);
    }

    private <T> void validate(List<T> value, Comparator<T> comparator) {
        if (value == null || value.isEmpty() || comparator == null) {
            throw new IllegalArgumentException("empty collection or comparator = null");
        }
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        validate(value, comparator);
        return find(value, comparator.reversed());
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 4, -2, 7, 2);
        MaxMin maxMin = new MaxMin();
        System.out.println("max=" + maxMin.max(list, Comparator.naturalOrder()));
        System.out.println("min=" + maxMin.min(list, Comparator.naturalOrder()));
    }
}
