package ru.job4j.design.lsp;

public class WarehouseStorage extends FoodStorage {
    public WarehouseStorage() {
        super(food -> {
            double procent = expiredInPercent(food);
            return (procent < 25.0) ? food : null;
        });
    }
}
