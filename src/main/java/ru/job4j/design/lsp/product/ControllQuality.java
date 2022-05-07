package ru.job4j.design.lsp.product;

import java.util.List;

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
