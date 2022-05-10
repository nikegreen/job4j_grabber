package ru.job4j.design.lsp.product;

import org.junit.Test;
import ru.job4j.design.lsp.product.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void expiredInPercentTestWarehouse() {
       ControllQuality controllQuality = new ControllQuality(
               List.of(
               new WarehouseStorage(),
               new ShopStorage(),
               new TrashStorage()
               )
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
       assertTrue(controllQuality.add(food));
       assertEquals(controllQuality.getAll().get(0).getStorage(), List.of(food));
       assertEquals(controllQuality.getAll().get(1).getStorage(), List.of());
       assertEquals(controllQuality.getAll().get(2).getStorage(), List.of());
    }

    @Test
    public void expiredInPercentTestShop1() {
        ControllQuality controllQuality = new ControllQuality(
                List.of(
                        new WarehouseStorage(),
                        new ShopStorage(),
                        new TrashStorage()
                )
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
        assertTrue(controllQuality.add(food));
        assertEquals(controllQuality.getAll().get(0).getStorage(), List.of());
        assertEquals(controllQuality.getAll().get(1).getStorage(), List.of(food));
        assertEquals(controllQuality.getAll().get(2).getStorage(), List.of());
        assertEquals(food.getDiscount(), food
                .getPrice()
                .multiply(BigDecimal.valueOf(0)));
    }

    @Test
    public void expiredInPercentTestShop2() {
        ControllQuality controllQuality = new ControllQuality(
                List.of(
                        new WarehouseStorage(),
                        new ShopStorage(),
                        new TrashStorage()
                )
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
        assertTrue(controllQuality.add(food));
        assertEquals(controllQuality.getAll().get(0).getStorage(), List.of());
        assertEquals(controllQuality.getAll().get(1).getStorage(), List.of(food));
        assertEquals(controllQuality.getAll().get(2).getStorage(), List.of());
        assertEquals(food.getDiscount(), food
                        .getPrice()
                        .multiply(BigDecimal.valueOf(0.2))
                        .setScale(2, RoundingMode.UP));
    }

    @Test
    public void expiredInPercentTestTrash() {
        ControllQuality controllQuality = new ControllQuality(
                List.of(
                        new WarehouseStorage(),
                        new ShopStorage(),
                        new TrashStorage()
                )
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
        assertTrue(controllQuality.add(food));
        assertEquals(controllQuality.getAll().get(0).getStorage(), List.of());
        assertEquals(controllQuality.getAll().get(1).getStorage(), List.of());
        assertEquals(controllQuality.getAll().get(2).getStorage(), List.of(food));
    }

    @Test
    public void resortTest() {
        ControllQuality controllQuality = new ControllQuality(
                List.of(
                        new WarehouseStorage(),
                        new ShopStorage(),
                        new TrashStorage()
                )
        );
        Calendar warehouseExpired = Calendar.getInstance();
        warehouseExpired.add(Calendar.MONTH, 9);
        Calendar warehouseCreated = Calendar.getInstance();
        warehouseCreated.add(Calendar.MONTH, -1);
        Food warehouseFood = new Food(
                "meat",
                warehouseExpired,
                warehouseCreated,
                new BigDecimal(400),
                new BigDecimal(0)
        );
        Calendar shop2Expired = Calendar.getInstance();
        shop2Expired.add(Calendar.MONTH, 1);
        Calendar shop2Created = Calendar.getInstance();
        shop2Created.add(Calendar.MONTH, -9);
        Food shop2Food = new Food(
                "meat",
                shop2Expired,
                shop2Created,
                new BigDecimal(400),
                new BigDecimal(0)
        );
        Calendar expired = Calendar.getInstance();
        expired.add(Calendar.MONTH, -1);
        Calendar created = Calendar.getInstance();
        created.add(Calendar.MONTH, -11);
        Food trashFood = new Food(
                "meat",
                expired,
                created,
                new BigDecimal(400),
                new BigDecimal(0)
        );
        FoodStorage.setCalendarNow(() -> {
            Calendar now = Calendar.getInstance();
            now.add(Calendar.MONTH, -10);
            return now;
        });
        assertTrue(controllQuality.add(warehouseFood));
        assertTrue(controllQuality.add(shop2Food));
        assertTrue(controllQuality.add(trashFood));
        FoodStorage.setCalendarNow(Calendar::getInstance);
        controllQuality.resort();
        assertEquals(controllQuality.getAll().get(0).getStorage(), List.of(warehouseFood));
        assertEquals(controllQuality.getAll().get(1).getStorage(), List.of(shop2Food));
        assertEquals(controllQuality.getAll().get(2).getStorage(), List.of(trashFood));
    }
}