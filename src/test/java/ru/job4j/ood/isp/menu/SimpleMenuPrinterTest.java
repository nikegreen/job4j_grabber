package ru.job4j.ood.isp.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleMenuPrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void printerTest() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertEquals(
                new Menu.MenuItemInfo(
                        "Сходить в магазин", List.of("Купить продукты"), STUB_ACTION, "1."
                ),
                menu.select("Сходить в магазин").orElse(null)
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Купить продукты",
                        List.of("Купить хлеб", "Купить молоко"),
                        STUB_ACTION,
                        "1.1."
                ),
                menu.select("Купить продукты").orElse(null)
        );
        assertEquals(
                new Menu.MenuItemInfo(
                        "Покормить собаку", List.of(), STUB_ACTION, "2."
                ),
                menu.select("Покормить собаку").orElse(null)
        );
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        menuPrinter.print(menu);
        assertEquals("Сходить в магазин 1.\n"
                + "--- Купить продукты 1.1.\n"
                + "------ Купить хлеб 1.1.1.\n"
                + "------ Купить молоко 1.1.2.\n"
                + "Покормить собаку 2.\n",
                output.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}