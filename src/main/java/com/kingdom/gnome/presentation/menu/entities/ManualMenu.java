package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;

import java.util.List;
import java.util.Scanner;

public class ManualMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final List<Gnome> gnomes; // Ссылка на общую армию

    public ManualMenu(MenuRoutes routeID, GnomeCreationStrategy strategy, List<Gnome> gnomes) {
        super(routeID);
        this.strategy = strategy;
        this.gnomes = gnomes;
    }

    @Override
    public void show() {
        System.out.println("--- Ручной глиномес гномов ---");
        System.out.println("1. Выдуть жидкого гнома через трубку");
        System.out.println("2. Посмотреть список текущих рабов");
        System.out.println("3. Вернуться на главную");
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
                    System.out.println("Ручной продув успех, выдавлены из пробирки!");
                }
                yield MenuRoutes.MANUAL;
            }
            case 2 -> {
                System.out.println("--- Состав армии ---");
                if (gnomes.isEmpty()) {
                    System.out.println("Армия пуста. мало атмосфер дуешь!");
                } else {
                    for (Gnome g : gnomes) {
                        System.out.println(g);
                    }
                }
                System.out.println("--------------------");
                yield MenuRoutes.MANUAL; // Остаемся в этом же меню
            }
            case 3 -> MenuRoutes.MAIN; // Уходим на главное меню

            default -> {
                System.out.println("Неизвестная команда. Попробуй еще раз.");
                yield MenuRoutes.MANUAL;
            }
        };
    }
}