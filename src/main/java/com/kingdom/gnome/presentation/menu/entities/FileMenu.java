package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.service.GnomeDataService;
import com.kingdom.gnome.service.GnomePrintService;

import java.util.ArrayList;
import java.util.List;

public class FileMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final GnomeDataService dataService;
    private final GnomePrintService printService;
    private final ConsoleInputReader inputReader;

    public FileMenu(MenuRoutes routeID, GnomeCreationStrategy strategy, GnomeDataService dataService, GnomePrintService printService, ConsoleInputReader inputReader) {
        super(routeID);
        this.strategy = strategy;
        this.dataService = dataService;
        this.printService = printService;
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
            case 1 -> {
                dataService.generateGnomes(strategy, inputReader);
                printService.showMessage("Гномы успешно десериализованы и добавлены в армию!");
                yield MenuRoutes.FILE;
            }
            case 2 -> {
                printService.showGnomes();
                yield MenuRoutes.FILE;
            }
            case 3 -> MenuRoutes.MAIN;
            default -> throw new IllegalStateException("Сломалась валидация в readIntInRange");
        };
    }
}