package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;

import java.util.List;
import java.util.Scanner;

public class FileMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final List<Gnome> gnomes; // Ссылка на нашу общую армию

    public FileMenu(MenuRoutes routeID, GnomeCreationStrategy strategy, List<Gnome> gnomes) {
        super(routeID);
        this.strategy = strategy;
        this.gnomes = gnomes;
    }

    @Override
    public void show() {
        System.out.println("--- Работа с файлами ---");
        System.out.println("1. Десериализовать гомогомгномов из файла");
        System.out.println("2. Посмотреть список гномов");
        System.out.println("3. На главную");
        System.out.print("Твой выбор: ");
    }

    @Override
    public MenuRoutes execute(Scanner scanner) {
        var num = scanner.nextInt();

        return switch (num) {
            case 1 -> {
                List<Gnome> freshGnomes = strategy.create(scanner);

                if (freshGnomes != null && !freshGnomes.isEmpty()) {
                    gnomes.addAll(freshGnomes);
                    System.out.println("Файловые гномы успешно десериализованы и добавлены в армию!");
                }
                yield MenuRoutes.FILE;
            }
            case 2 -> {
                System.out.println("--- Состав армии ---");
                if (gnomes.isEmpty()) {
                    System.out.println("В RAM-помойке пока пусто.");
                } else {
                    for (Gnome g : gnomes) {
                        System.out.println(g);
                    }
                }
                System.out.println("--------------------");
                yield MenuRoutes.FILE;
            }
            case 3 -> MenuRoutes.MAIN;

            default -> {
                System.out.println("Произошел GnomiyChackChackException. Попробуй еще раз.");
                yield MenuRoutes.FILE;
            }
        };
    }
}