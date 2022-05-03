package ru.job4j.design.lsp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.function.Function;
import java.util.function.Predicate;

public class ControllQuality extends BaseControllQuality {

    public ControllQuality(
            FoodStorage warehouse,
            FoodStorage shop,
            FoodStorage trash
    ) {
        super(
                warehouse,
                shop,
                trash,
                food -> expiredInPercent(food) < 25.0,
                food -> {
                    double res = expiredInPercent(food);
                    return 25 <= res && res < 100;
                },
                food -> expiredInPercent(food) >= 100
        );
        setFunctionShop(food -> {
                    if (expiredInPercent(food) >= 75) {
                        food.setDiscount(food.getPrice()
                                .multiply(BigDecimal.valueOf(0.2))
                                .setScale(2, RoundingMode.UP)
                        );
                    }
                    return food;
                }
        );
    }

    public static double expiredInPercent(Food food) {
        Calendar now = Calendar.getInstance();
        return 100.0 * (now.getTimeInMillis()
                - food.getCreateDate().getTimeInMillis())
                / (food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis());
    }
}
