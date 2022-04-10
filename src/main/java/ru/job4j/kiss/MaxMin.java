package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        if (value == null || value.isEmpty() || comparator == null) {
            throw new IllegalArgumentException("empty collection or comparator = null");
        }
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

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Comparator<T> comparatorMin = new Comparator<>() {
            @Override
            public int compare(T o1, T o2) {
                return comparator.compare(o2, o1);
            }
        };
        return max(value, comparator == null ? comparator : comparatorMin);
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 4, -2, 7, 2);
        MaxMin maxMin = new MaxMin();
        System.out.println("max=" + maxMin.max(list, Comparator.naturalOrder()));
        System.out.println("min=" + maxMin.min(list, Comparator.naturalOrder()));
    }
}
