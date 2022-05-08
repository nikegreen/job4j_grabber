package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements  MenuPrinter {
    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iter = menu.iterator();
        while (iter.hasNext()) {
            String prefix = "";
            Menu.MenuItemInfo info = iter.next();
            int index = info.getNumber().indexOf(".");
            while ((index = info.getNumber().indexOf(".", index + 1)) >= 0) {
                System.out.print("---");
                prefix = " ";
            }
            System.out.println(prefix + info.getName() + " " + info.getNumber());
        }
    }
}
