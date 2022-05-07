package ru.job4j.design.lsp.product;

import ru.job4j.design.lsp.product.FoodStorage;

public class TrashStorage extends FoodStorage {
    public TrashStorage() {
        super(food -> expiredInPercent(food) >= 100);
    }
}
