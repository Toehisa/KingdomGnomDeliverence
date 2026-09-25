package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;

import java.util.List;

public class RandomMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final List<Gnome> gnomes;
    private final ConsoleInputReader inputReader;

    public RandomMenu(MenuRoutes routeID, GnomeCreationStrategy strategy, List<Gnome> gnomes, ConsoleInputReader inputReader) {
        super(routeID);
        this.strategy = strategy;
        this.gnomes = gnomes;
        this.inputReader = inputReader;
    }

    @Override
    public void show() {
        System.out.println("1.Приступить к генерации");
        System.out.println("2.Посмотреть список сгенереных гномов");
        System.out.println("3.На главную");
    }

    @Override
    public MenuRoutes execute() {
        int num = inputReader.readInteger("Твой ответ, хозяин: ",1,3);
        return switch (num) {
            case 1 -> {
                List<Gnome> freshGnomes = strategy.create(inputReader);
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
};
