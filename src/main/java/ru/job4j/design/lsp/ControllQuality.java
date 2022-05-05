package ru.job4j.design.lsp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ControllQuality {
    private final List<FoodStorage> storages;

    public ControllQuality(List<FoodStorage> storages) {
            this.storages = storages;
    }

    public boolean add(Food food) {
        boolean result = false;
        for (FoodStorage storage: storages
             ) {
            result = storage.add(food);
            if (result) {
                break;
            }
        }
        return result;
    }

    public List<FoodStorage> getAll() {
        return storages;
    }
}
