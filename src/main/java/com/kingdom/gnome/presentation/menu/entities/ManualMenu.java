package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import static com.kingdom.gnome.presentation.menu.providers.random.GnomeEmailProvider.EMAILS;

import java.util.List;

public class ManualMenu extends Menu {
    private final GnomeCreationStrategy strategy;
    private final List<Gnome> gnomes;
    private final ConsoleInputReader inputReader;

    public ManualMenu(
            MenuRoutes routeID,
            GnomeCreationStrategy strategy,
            List<Gnome> gnomes,
            ConsoleInputReader inputReader
    ) {
        super(routeID);
        this.strategy = strategy;
        this.gnomes = gnomes;
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
            case 1 -> startGeneration();
            case 2 -> showGnomes();
            case 3 -> MenuRoutes.MAIN;
            default -> null;
        };
    }
    //пока что вынес сюда, потом уже по солиду в другие сущности вынесу
    private MenuRoutes startGeneration() {
        strategy.createGnomes(inputReader, gnomes);
        System.out.println("Ручной продув успех, выдавлены из пробирки!");
        return MenuRoutes.MANUAL;
    }
    //пока что вынес сюда, потом уже по солиду в другие сущности вынесу
    private MenuRoutes showGnomes() {
        System.out.println("--- Состав армии ---");
        if (gnomes.isEmpty()) {
            System.out.println("Армия пуста. мало атмосфер дуешь!");
        } else {
            for (Gnome g : gnomes) {
                System.out.println(g);
            }
        }
        System.out.println("--------------------");
        return MenuRoutes.MANUAL;
    }
}