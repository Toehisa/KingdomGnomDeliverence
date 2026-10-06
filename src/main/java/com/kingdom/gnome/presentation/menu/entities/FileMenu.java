package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import java.util.ArrayList;
import java.util.List;

public class FileMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final List<Gnome> gnomes;
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
        System.out.println("1. Десериализовать гномов из файла");
        System.out.println("2. Посмотреть список гномов");
        System.out.println("3. На главную");
    }

    @Override
    public MenuRoutes execute() {
        int num = inputReader.readIntInRange("Твой ответ, хозяин: ",1,3);
        return switch (num) {
            case 1 -> startGeneration();
            case 2 -> showGnomes();
            case 3 -> MenuRoutes.MAIN;
            default -> {
                System.out.println("Произошел GnomicChackChackException. Попробуй еще раз.");
                yield MenuRoutes.FILE;
            }
        };
    }

    //пока что вынес сюда, потом уже по солиду в другие сущности вынесу
    private MenuRoutes startGeneration() {
        strategy.createGnomes(inputReader, gnomes);
        System.out.println("Гномы успешно десериализованы и добавлены в армию!");
        return MenuRoutes.FILE;
    }
    //пока что вынес сюда, потом уже по солиду в другие сущности вынесу
    private MenuRoutes showGnomes() {
        System.out.println("--- Состав армии ---");
        if (gnomes.isEmpty()) {
            System.out.println("В RAM пока пусто.");
        } else {
            for (Gnome g : gnomes) {
                System.out.println(g);
            }
        }
        System.out.println("--------------------");
        return MenuRoutes.FILE;
    }
}