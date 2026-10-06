package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.service.GnomeDataService;
import com.kingdom.gnome.service.GnomePrintService;
import com.kingdom.gnome.service.sorting.SortOption;

public class SortMenu extends Menu {
    private final GnomeDataService dataService;
    private final GnomePrintService printService;
    private final ConsoleInputReader inputReader;

    public SortMenu(MenuRoutes routeID, GnomeDataService dataService, GnomePrintService printService, ConsoleInputReader inputReader) {
        super(routeID);
        this.dataService = dataService;
        this.printService = printService;
        this.inputReader = inputReader;
    }

    @Override
    public boolean canEnter() {
        return !dataService.isEmpty();
    }

    @Override
    public String denyMessage() {
        return "Армия пуста! Сначала создайте гномов.";
    }

    @Override
    public void show() {
        System.out.println("\n--- Сортировка орды гномов ---");
        SortOption[] types = SortOption.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i].getLabel());
        }
        System.out.println((types.length + 1) + ". Вернуться на главную");
    }

    @Override
    public MenuRoutes execute() {

        int backOption = SortOption.values().length + 1;
        int num = inputReader.readIntInRange("Твой ответ: ", 1, backOption);

        if (num == backOption) {
            return MenuRoutes.MAIN;
        }

        SortOption selectedType = SortOption.values()[num - 1];

        dataService.sortAndSave(selectedType);
        printService.showMessage("Гномы успешно отсортированы и записаны в файл");

        return MenuRoutes.SORT;
    }
}

