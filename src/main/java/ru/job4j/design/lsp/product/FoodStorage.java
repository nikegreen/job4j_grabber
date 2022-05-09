package ru.job4j.design.lsp.product;

import ru.job4j.design.lsp.product.Food;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public abstract class FoodStorage {
    static private Supplier<Calendar> calendarNow;
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

    static {
        setCalendarNow(Calendar::getInstance);
    }

    public static void setCalendarNow(Supplier<Calendar> now) {
        calendarNow = now;
    }

    public static double expiredInPercent(Food food) {
        Calendar now = calendarNow.get();
        return 100.0 * (now.getTimeInMillis()
                - food.getCreateDate().getTimeInMillis())
                / (food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis());
    }
}
