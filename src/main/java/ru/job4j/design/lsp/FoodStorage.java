package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;

public class FoodStorage {
    private final List<Food> storage = new ArrayList<>();
    private final Function<Food, Food> function;

    public FoodStorage(Function<Food, Food> functionAccept) {
        this.function = functionAccept;
    }

    public boolean add(Food food) {
       boolean res = false;
       if (!storage.contains(food)) {
           food = function.apply(food);
           if (food != null) {
               res = storage.add(food);
           }
       }
       return res;
    }

    public boolean remove(Food food) {
        return storage.remove(food);
    }

    public List<Food> getStorage() {
        return storage;
    }

    public static double expiredInPercent(Food food) {
        Calendar now = Calendar.getInstance();
        return 100.0 * (now.getTimeInMillis()
                - food.getCreateDate().getTimeInMillis())
                / (food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis());
    }
}
