package ru.job4j.design.lsp;

public class WarehouseStorage extends FoodStorage {
    public WarehouseStorage() {
        super(food -> expiredInPercent(food) < 25.0);
    }
}
