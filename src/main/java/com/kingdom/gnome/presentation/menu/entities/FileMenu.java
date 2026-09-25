package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import java.util.List;

public class FileMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final List<Gnome> gnomes; // Ссылка на нашу общую армию
    private final ConsoleInputReader inputReader;

    public FileMenu(MenuRoutes routeID, GnomeCreationStrategy strategy, List<Gnome> gnomes, ConsoleInputReader inputReader) {
        super(routeID);
        this.strategy = strategy;
        this.gnomes = gnomes;
        this.inputReader = inputReader;
    }

    @Override
    public void show() {
        System.out.println("--- Работа с файлами ---");
        System.out.println("1. Десериализовать гомогомгномов из файла");
        System.out.println("2. Посмотреть список гномов");
        System.out.println("3. На главную");
    }

    @Override
    public MenuRoutes execute() {

        int num = inputReader.readInteger("Твой ответ, хозяин: ",1,3);
        return switch (num) {
            case 1 -> {
                List<Gnome> freshGnomes = strategy.create(inputReader);

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