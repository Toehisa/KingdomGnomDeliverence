package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.service.GnomeDataService;
import com.kingdom.gnome.service.GnomePrintService;

public class ManualMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final GnomeDataService dataService;
    private final GnomePrintService printService;
    private final ConsoleInputReader inputReader;

    public ManualMenu(
            MenuRoutes routeID,
            GnomeCreationStrategy strategy,
            GnomeDataService dataService,
            GnomePrintService printService,
            ConsoleInputReader inputReader
    ) {
        super(routeID);
        this.strategy = strategy;
        this.dataService = dataService;
        this.printService = printService;
        this.inputReader = inputReader;
    }
    @Override
    public void show() {
        System.out.println("--- Ручной глиномес гномов ---");
        System.out.println("1. Выдуть жидкого гнома через трубку");
        System.out.println("2. Посмотреть список текущих рабов");
        System.out.println("3. Вернуться на главную");
    }

    @Override
    public MenuRoutes execute() {
        int num = inputReader.readIntInRange("Твой ответ, хозяин: ",1,3);
        return switch (num) {
            case 1 -> {
                dataService.generateGnomes(strategy, inputReader);
                printService.showMessage("Ручной продув успех, выдавлены из пробирки!");
                yield MenuRoutes.MANUAL;
            }
            case 2 -> {
                printService.showGnomes();
                yield MenuRoutes.MANUAL;
            }
            case 3 -> MenuRoutes.MAIN;
            default -> throw new IllegalStateException("Сломалась валидация в readIntInRange");
        };
    }
}