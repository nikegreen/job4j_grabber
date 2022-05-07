package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class FoodStorage {
    private final List<Food> storage = new ArrayList<>();
    private final Predicate<Food> accept;

    protected FoodStorage(Predicate<Food> accept) {
        this.accept = accept;
    }

    public boolean add(Food food) {
       boolean res = false;
       if (!storage.contains(food) && accept.test(food)) {
               res = storage.add(food);
       }
       return res;
    }

    public boolean remove(Food food) {
        return storage.remove(food);
    }

    public List<Food> getStorage() {
        return List.copyOf(storage);
    }

    public static double expiredInPercent(Food food) {
        Calendar now = Calendar.getInstance();
        return 100.0 * (now.getTimeInMillis()
                - food.getCreateDate().getTimeInMillis())
                / (food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis());
    }
}
