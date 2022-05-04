package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parking {
    private final List<Parkable> cells;

    public Parking(int cellsSize) {
        this.cells = new ArrayList<>(cellsSize);
        while (cellsSize > 0) {
            cells.add(null);
            cellsSize--;
        }
    }

    public boolean put(Parkable car) {
        boolean result = false;
        int size = car.getCells();
        for (int i = 0; i < cells.size() - size + 1; i++) {
            if (cells.get(i) == null) {
                int j = i + size - 1;
                while (j > i && cells.get(j) == null) {
                    j--;
                }
                result = j == i;
                if (result) {
                    for (j = i; j < i + size; j++) {
                        cells.set(j, car);
                    }
                    break;
                }
                i = j;
            }
        }
        return result;
    }

    public boolean free(Parkable car) {
        return Collections.replaceAll(cells, car, null);
    }
}
