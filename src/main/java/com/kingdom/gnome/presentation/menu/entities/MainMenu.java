package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import static com.kingdom.gnome.presentation.menu.routes.MenuRoutes.*;

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
        System.out.println("4. Глянуть на текущую армию");
        System.out.println("5. Распределить армию в нужную ротацию");
        System.out.println("6. Доблестно ливнуть с тылом под гномий ансамбль");

    }


    @Override
    public MenuRoutes execute() {
        return inputReader.readUntilValid(
                "Введите число: ",
                (userInput) -> MenuRoutes.fromStr(userInput, RANDOM, MANUAL, FILE,
                        GNOMES,
                        SORT,
                        EXIT)
        );
    }
}