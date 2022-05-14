package ru.job4j.ood.isp.menu;

import java.io.PrintStream;
import java.util.Iterator;

public class SimpleMenuPrinter implements  MenuPrinter {
    private static final String INDENT = "---";
    private final PrintStream printStream;

    public SimpleMenuPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo info : menu) {
            int menuLevel = info.getNumber().split("\\.").length - 1;
            for (int index = menuLevel; index > 0; index--) {
                printStream.print(INDENT);
            }
            String prefix = menuLevel > 0 ? " " : "";
            printStream.println(prefix + info.getName() + " " + info.getNumber());
        }
    }
}
