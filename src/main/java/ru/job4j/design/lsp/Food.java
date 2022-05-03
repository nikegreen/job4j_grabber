package ru.job4j.design.lsp;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Objects;

public class Food {
    private final String name;
    private final Calendar expiryDate;
    private final Calendar createDate;
    private final BigDecimal price;
    private BigDecimal discount;

    public Food(String name,
                Calendar expiryDate,
                Calendar createDate,
                BigDecimal price,
                BigDecimal discount
    ) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food)) {
            return false;
        }
        Food food = (Food) o;
        return Objects.equals(getName(),
                food.getName()) && Objects.equals(getExpiryDate(),
                food.getExpiryDate()) && Objects.equals(getCreateDate(),
                food.getCreateDate()) && Objects.equals(getPrice(),
                food.getPrice()) && Objects.equals(getDiscount(),
                food.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getExpiryDate(), getCreateDate(), getPrice(), getDiscount());
    }
}
