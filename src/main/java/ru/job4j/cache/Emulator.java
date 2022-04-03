package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Emulator {
    private String[] mainMenuStr = {
            "Настроить каталог с файлами.",
            "загрузить содержимое файла в кэш",
            "получить содержимое файла из кэша",
            "Закончить."
    };

    private Scanner scanner = null;

    private String directory = "/home/nike/projects/job4j_grabber/src/main/java/ru/job4j/cache";

    private AbstractCache<String, String> cache = null;

    public Emulator(Scanner scanner) {
        this.scanner = scanner;
    }

    private void scannerClear() {
        scanner.nextLine();
    }

    private void runMenu(String[] menu) {
        int choice;
        boolean loop = true;
        do {
            do {
                int i = 0;
                for (String s : menu) {
                    System.out.println(i++ + ": " + s);
                }
                System.out.println("Введите номер пункта меню:");
                choice = scanner.nextInt();
                scannerClear();
            } while (choice < 0 || choice >= menu.length);
            switch (choice) {
                case 0:
                    setDirectory();
                    break;
                case 1:
                    loadFromFile();
                    break;
                case 2:
                    getCache();
                    break;
                default:
                    loop = false;
                    break;
            }
        } while (loop);
    }

    private void getCache() {
        System.out.println("input file name:");
        String fileName = scanner.nextLine();
        String str = cache.get(fileName);
        if (str != null) {
            System.out.println("file:" + fileName);
            System.out.println("{" + str + "}");
        } else {
            System.out.println("key (" + fileName + ") is not exist!");
        }
    }

    private void loadFromFile() {
        System.out.println("load from file:");
        String fileName = scanner.nextLine();
        Path path = Paths.get(directory, fileName);
        if (Files.exists(path)) {
            System.out.println("file:" + fileName);
            String str = cache.load(fileName);
            System.out.println("{" + str + "}");
            cache.put(fileName, str);
        } else {
            System.out.println("path or file is not exist!(" + path.toString() + ")");
        }
    }

    private void setDirectory() {
        System.out.println("old directory:" + directory);
        System.out.print("input new directory:");
        directory = scanner.nextLine();
        Path path = Path.of(directory);
        if (Files.exists(path)) {
            cache = new DirFileCache(directory);
        } else {
            System.out.println("Directory '" + path + "' is not exist!");
        }
    }

    public void run() {
        runMenu(mainMenuStr);
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator(new Scanner(System.in));
        emulator.run();
    }
}
