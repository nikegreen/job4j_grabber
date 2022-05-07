package ru.job4j.design.lsp.product;

import ru.job4j.design.lsp.product.FoodStorage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShopStorage extends FoodStorage {
    public ShopStorage() {
        super(food -> {
            double period = expiredInPercent(food);
            boolean result = (25.0 <= period) && (period < 100.0);
            if (result) {
                if (period >= 75.0) {
                    food.setDiscount(food.getPrice()
                            .multiply(BigDecimal.valueOf(0.2))
                            .setScale(2, RoundingMode.UP)
                    );
                }
            }
            return  result;
        });
    }
}
