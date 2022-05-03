package ru.job4j.design.lsp;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void expiredInPercentTestWarehouse() {
       FoodStorage warehouse = new FoodStorage();
       FoodStorage shop = new FoodStorage();
       FoodStorage trash = new FoodStorage();
       ControllQuality controllQuality = new ControllQuality(
               warehouse,
               shop,
               trash
       );
       Calendar expired = Calendar.getInstance();
       expired.add(Calendar.MONTH, 9);
       Calendar created = Calendar.getInstance();
       created.add(Calendar.MONTH, -1);
       Food food = new Food(
               "meat",
               expired,
               created,
               new BigDecimal(400),
               new BigDecimal(0)
       );
       assertTrue(controllQuality.execute(food));
       assertEquals(warehouse.getStorage(), List.of(food));
       assertEquals(shop.getStorage(), List.of());
       assertEquals(trash.getStorage(), List.of());
    }

    @Test
    public void expiredInPercentTestShop1() {
        FoodStorage warehouse = new FoodStorage();
        FoodStorage shop = new FoodStorage();
        FoodStorage trash = new FoodStorage();
        ControllQuality controllQuality = new ControllQuality(
                warehouse,
                shop,
                trash
        );
        Calendar expired = Calendar.getInstance();
        expired.add(Calendar.MONTH, 5);
        Calendar created = Calendar.getInstance();
        created.add(Calendar.MONTH, -5);
        Food food = new Food(
                "meat",
                expired,
                created,
                new BigDecimal(400),
                new BigDecimal(0)
        );
        assertTrue(controllQuality.execute(food));
        assertEquals(warehouse.getStorage(), List.of());
        assertEquals(shop.getStorage(), List.of(food));
        assertEquals(trash.getStorage(), List.of());
        assertEquals(food.getDiscount(), food
                .getPrice()
                .multiply(BigDecimal.valueOf(0)));
    }

    @Test
    public void expiredInPercentTestShop2() {
        FoodStorage warehouse = new FoodStorage();
        FoodStorage shop = new FoodStorage();
        FoodStorage trash = new FoodStorage();
        ControllQuality controllQuality = new ControllQuality(
                warehouse,
                shop,
                trash
        );
        Calendar expired = Calendar.getInstance();
        expired.add(Calendar.MONTH, 1);
        Calendar created = Calendar.getInstance();
        created.add(Calendar.MONTH, -9);
        Food food = new Food(
                "meat",
                expired,
                created,
                new BigDecimal(400),
                new BigDecimal(0)
        );
        assertTrue(controllQuality.execute(food));
        assertEquals(warehouse.getStorage(), List.of());
        assertEquals(shop.getStorage(), List.of(food));
        assertEquals(trash.getStorage(), List.of());
        assertEquals(food.getDiscount(), food
                        .getPrice()
                        .multiply(BigDecimal.valueOf(0.2))
                        .setScale(2, RoundingMode.UP));
    }

    @Test
    public void expiredInPercentTestTrash() {
        FoodStorage warehouse = new FoodStorage();
        FoodStorage shop = new FoodStorage();
        FoodStorage trash = new FoodStorage();
        ControllQuality controllQuality = new ControllQuality(
                warehouse,
                shop,
                trash
        );
        Calendar expired = Calendar.getInstance();
        expired.add(Calendar.MONTH, -1);
        Calendar created = Calendar.getInstance();
        created.add(Calendar.MONTH, -11);
        Food food = new Food(
                "meat",
                expired,
                created,
                new BigDecimal(400),
                new BigDecimal(0)
        );
        assertTrue(controllQuality.execute(food));
        assertEquals(warehouse.getStorage(), List.of());
        assertEquals(shop.getStorage(), List.of());
        assertEquals(trash.getStorage(), List.of(food));
    }
}