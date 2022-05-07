package ru.job4j.design.lsp.product;

import ru.job4j.design.lsp.product.FoodStorage;

public class WarehouseStorage extends FoodStorage {
    public WarehouseStorage() {
        super(food -> expiredInPercent(food) < 25.0);
    }
}
