package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;

import java.util.Scanner;

public class MainMenu extends Menu{
    public MainMenu(MenuRoutes routeID) {
        super(routeID);
    }

    @Override
    public void show() {
        System.out.println("1.Закуканить случайным образом новых домашних рабов, гномов");
        System.out.println("2.Слепить жидких гномов вручную");
        System.out.println("3.Десереализовать гномов в ram помойку из файла");
        System.out.println("4.Распределить армию в нужную ротацию");
        System.out.println("5.Доблестно ливнуть с тильтом под гномий ансамбль");
        System.out.print("Твой выбор: ");
    }

    @Override
    public MenuRoutes execute(Scanner scanner) {
        int num = readInteger(scanner);
        return switch (num) {
            case 1 -> MenuRoutes.RANDOM;
            case 2 -> MenuRoutes.MANUAL;
            case 3 -> MenuRoutes.FILE;
            case 5 -> MenuRoutes.EXIT;
            default -> MenuRoutes.EXCEPTION;
        };
    }
    private int readInteger(Scanner scanner) {

        while (true) {

            System.out.print("Твой выбор: ");

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ты мне вводи цифры, а не буквы");
            }
        }
    }
}
