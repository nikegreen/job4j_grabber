package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements  MenuPrinter {
    private static final String INDENT = "---";

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iter = menu.iterator();
        while (iter.hasNext()) {
            String prefix = "";
            Menu.MenuItemInfo info = iter.next();
            int index = info.getNumber().split("\\.").length - 1;
            while (index--  > 0) {
                System.out.print(INDENT);
                prefix = " ";
            }
            System.out.println(prefix + info.getName() + " " + info.getNumber());
        }
    }
}
