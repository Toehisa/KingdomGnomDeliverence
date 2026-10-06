package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;
import com.kingdom.gnome.service.GnomeDataService;
import com.kingdom.gnome.service.GnomePrintService;

public class RandomMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final GnomeDataService dataService;
    private final GnomePrintService printService;
    private final ConsoleInputReader inputReader;

    public RandomMenu(MenuRoutes routeID, GnomeCreationStrategy strategy, GnomeDataService gnomeDataService, GnomePrintService printService, ConsoleInputReader inputReader) {
        super(routeID);
        this.strategy = strategy;
        this.dataService = gnomeDataService;
        this.inputReader = inputReader;
        this.printService = printService;
    }

    @Override
    public void show() {
        System.out.println("1.Приступить к генерации");
        System.out.println("2.Посмотреть список сгенереных гномов");
        System.out.println("3.На главную");
    }

    @Override
    public MenuRoutes execute() {
        int num = inputReader.readIntInRange("Твой ответ, хозяин: ",1,3);
        return switch (num) {
            case 1 -> {
                dataService.generateGnomes(strategy, inputReader);
                printService.showMessage("Гномы успешно добавлены в общую армию!");
                yield MenuRoutes.RANDOM;
            }
            case 2 -> {
                printService.showGnomes();
                yield MenuRoutes.RANDOM;
            }
            case 3 -> MenuRoutes.MAIN;
            default -> throw new IllegalStateException("Сломалась валидация в readIntInRange");
        };
    }
};
