package ru.job4j.design.lsp.product;

import java.util.ArrayList;
import java.util.List;
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

    public void resort() {
        ArrayList<Food> storage = new ArrayList<>();
        for (FoodStorage foodStorage: storages) {
            storage.addAll(foodStorage.getStorage());
            for (Food food:foodStorage.getStorage()) {
                foodStorage.remove(food);
            }
        }
        for (Food food: storage) {
            add(food);
        }
        storage.clear();
    }
}
