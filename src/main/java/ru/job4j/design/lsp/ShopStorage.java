package ru.job4j.design.lsp;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShopStorage extends FoodStorage {
    public ShopStorage() {
        super(food -> {
            double experied = expiredInPercent(food);
            if (25.0 <= experied && experied < 100.0) {
                if (experied >= 75.0) {
                    food.setDiscount(food.getPrice()
                            .multiply(BigDecimal.valueOf(0.2))
                            .setScale(2, RoundingMode.UP)
                    );
                }
            } else {
                food = null;
            }
            return  food;
        });
    }
}
