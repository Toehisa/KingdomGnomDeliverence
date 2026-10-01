package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.menu.routes.SearchRoutes;
import com.kingdom.gnome.service.counting.GnomeCounterService;

import java.util.List;

import static com.kingdom.gnome.presentation.menu.routes.MenuRoutes.EXIT;
import static com.kingdom.gnome.presentation.menu.routes.MenuRoutes.MAIN;

public class SearchMenu extends Menu{
    private final GnomeCounterService counterService;
    private final List<Gnome> gnomes;
    private final ConsoleInputReader inputReader;

    public SearchMenu(MenuRoutes routeID, GnomeCounterService counterService, List<Gnome> gnomes, ConsoleInputReader inputReader) {
        super(routeID);
        this.counterService = counterService;
        this.gnomes = gnomes;
        this.inputReader = inputReader;
    }

    @Override
    public void show() {
        System.out.println("\n--- ВЫБОР КРИТЕРИЯ ДЛЯ ПОДСЧЕТА ---");
        System.out.println("1. Посчитать по Имени");
        System.out.println("2. Посчитать по Роли");
        System.out.println("3. Посчитать по Email");
        System.out.println("4. Вернуться в главное меню");
    }

    @Override
    public MenuRoutes execute() {
        int num = inputReader.readIntInRange("Введите число: ", 1, 4);

        if (num == 4) return EXIT;

        SearchRoutes role = SearchRoutes.fromInt(num);
        String value = inputReader.readLine("Введите значение: ");

        int count = counterService.getOccurrencesCount(role.name(), value, gnomes);

        System.out.printf("Количество вхождений %s - %d\n", value, count);

        return MAIN;
    }
}
