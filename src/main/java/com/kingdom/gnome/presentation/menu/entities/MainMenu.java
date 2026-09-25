package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;

public class MainMenu extends Menu {

    private final ConsoleInputReader inputReader;

    public MainMenu(
            MenuRoutes routeID,
            ConsoleInputReader inputReader
    ) {
        super(routeID);
        this.inputReader = inputReader;
    }

    @Override
    public void show() {
        System.out.println("1. Закукать случайным образом новых домашних работ, гномов");
        System.out.println("2. Слепить жидких гномов вручную");
        System.out.println("3. Десериализовать гномов в рам помощью из файла");
        System.out.println("4. Распределить армию в нужную ротацию");
        System.out.println("5. Доблестно ливнуть с тылом под гномий ансамбль");

    }

    @Override
    public MenuRoutes execute() {

        int num = inputReader.readInteger("Твой ответ хозяин: ");

        return switch (num) {
            case 1 -> MenuRoutes.RANDOM;
            case 2 -> MenuRoutes.MANUAL;
            case 3 -> MenuRoutes.FILE;
            case 5 -> MenuRoutes.EXIT;
            default -> {
                System.out.println("Выбирай только тот пункт, который доступен - от 1 до 5: ");
                yield MenuRoutes.MAIN;
            }
        };
    }
}