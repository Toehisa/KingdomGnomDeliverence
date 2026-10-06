package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import static com.kingdom.gnome.presentation.menu.routes.MenuRoutes.*;

public class MainMenu extends Menu {

    private final ConsoleInputReader inputReader;

    public MainMenu(MenuRoutes routeID, ConsoleInputReader inputReader) {
        super(routeID);
        this.inputReader = inputReader;
    }

    @Override
    public void show() {
        System.out.println("1. Сгенерить случайным образом новых гномов");
        System.out.println("2. Слепить гномов вручную");
        System.out.println("3. Десериализовать гномов в кучу из файла");
        System.out.println("4. Глянуть на текущую армию");
        System.out.println("5. Распределить армию в нужную ротацию");
        System.out.println("6. Посчитать гномов по критерию на выбор");
        System.out.println("7. Доблестно ливнуть под гномий ансамбль");

    }


    @Override
    public MenuRoutes execute() {
        return inputReader.readUntilValid(
                "Введите число: ",
                (userInput) -> MenuRoutes.fromStr(
                        userInput,
                        RANDOM,
                        MANUAL,
                        FILE,
                        GNOMES,
                        SORT,
                        SEARCH,
                        EXIT)
        );
    }
}