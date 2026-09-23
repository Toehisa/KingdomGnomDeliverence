package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;

import java.util.List;
import java.util.Scanner;

public class RandomMenu extends Menu{
    private final GnomeCreationStrategy strategy;
    private final List<Gnome> gnomes;

    public RandomMenu(MenuRoutes routeID, GnomeCreationStrategy strategy, List<Gnome> gnomes) {
        super(routeID);
        this.strategy = strategy;
        this.gnomes = gnomes;
    }

    @Override
    public void show() {
        System.out.println("1.Приступить к генерации");
        System.out.println("2.Посмотреть список сгенереных гномов");
        System.out.println("3.На главную");
    }

    @Override
    public MenuRoutes execute(Scanner scanner) {
        var num = scanner.nextInt();
        return switch (num){
            case 1 -> {
                List<Gnome> freshGnomes = strategy.create(scanner);
                if (freshGnomes != null && !freshGnomes.isEmpty()) {
                    gnomes.addAll(freshGnomes);
                    System.out.println("Гномы успешно добавлены в общую армию!");
                }
                yield MenuRoutes.RANDOM;
            }
            case 2 -> {
                System.out.println("--- Список гномов ---");
                for (Gnome g : gnomes) {
                    System.out.println(g);
                }
                System.out.println("---------------------");
                yield MenuRoutes.RANDOM;
            }
            case 3 -> MenuRoutes.MAIN;

            default -> {
                System.out.println("Неверный ввод, попробуй еще раз.");
                yield MenuRoutes.RANDOM;
            }
        };
    }
}
