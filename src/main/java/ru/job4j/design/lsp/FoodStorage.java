package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class FoodStorage {
    private final List<Food> storage = new ArrayList<>();

    public boolean insert(Food food) {
       boolean res = !storage.contains(food);
       if (res) {
           storage.add(food);
       }
       return res;
    }

    public boolean remove(Food food) {
        return storage.remove(food);
    }

    public List<Food> getStorage() {
        return storage;
    }
}
