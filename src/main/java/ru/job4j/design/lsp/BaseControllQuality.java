package ru.job4j.design.lsp;

import java.util.function.Function;
import java.util.function.Predicate;

public class BaseControllQuality {
    private final FoodStorage shop;
    private final FoodStorage trash;
    private final FoodStorage warehouse;

    private final Predicate<Food> predicateWarehouse;

    private final Predicate<Food> predicateShop;

    private final Predicate<Food> predicateTrash;

    private Function<Food, Food> functionWarehouse;

    private Function<Food, Food> functionShop;

    private Function<Food, Food> functionTrash;

    @SuppressWarnings("checkstyle:ParameterNumber")
    public BaseControllQuality(
            FoodStorage warehouse,
            FoodStorage shop,
            FoodStorage trash,
            Predicate<Food> predicateWarehouse,
            Predicate<Food> predicateShop,
            Predicate<Food> predicateTrash
    ) {
        this.warehouse = warehouse;
        this.shop = shop;
        this.trash = trash;
        this.predicateWarehouse = predicateWarehouse;
        this.predicateShop = predicateShop;
        this.predicateTrash = predicateTrash;
        this.functionWarehouse = food -> food;
        this.functionShop = food -> food;
        this.functionTrash = food -> food;
    }

    final public boolean execute(Food food) {
        boolean res = false;
        if (predicateTrash.test(food)) {
            res = trash.insert(functionTrash.apply(food));
        } else if (predicateShop.test(food)) {
            res = shop.insert(functionShop.apply(food));
        } else if (predicateWarehouse.test(food)) {
            res = warehouse.insert(functionWarehouse.apply(food));
        }
        return res;
    }

    public void setFunctionWarehouse(Function<Food, Food> functionWarehouse) {
        this.functionWarehouse = functionWarehouse;
    }

    public void setFunctionShop(Function<Food, Food> functionShop) {
        this.functionShop = functionShop;
    }

    public void setFunctionTrash(Function<Food, Food> functionTrash) {
        this.functionTrash = functionTrash;
    }
}
