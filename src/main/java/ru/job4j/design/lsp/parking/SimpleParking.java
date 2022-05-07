package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleParking implements Parking {
    private final List<Parkable> cells;

    public SimpleParking(int cellsSize) {
        this.cells = new ArrayList<>(cellsSize);
        while (cellsSize > 0) {
            cells.add(null);
            cellsSize--;
        }
    }

    @Override
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

    @Override
    public boolean free(Parkable car) {
        return Collections.replaceAll(cells, car, null);
    }
}
