package ru.job4j.design.lsp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TrashStorage extends FoodStorage {
    public TrashStorage() {
        super(food -> expiredInPercent(food) >= 100);
    }
}
