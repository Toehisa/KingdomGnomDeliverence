package com.kingdom.gnome.presentation.menu.providers;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.entities.*;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.FileGnomeStrategy;
import com.kingdom.gnome.presentation.strategy.ManualGnomeStrategy;
import com.kingdom.gnome.presentation.strategy.RandomGnomeStrategy;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import java.util.List;
import java.util.Map;

public class MainMenuProvider extends MenuProvider {

    private final ConsoleInputReader inputReader;

    public MainMenuProvider(List<Gnome> gnomes, ConsoleInputReader inputReader) {
        super(gnomes);
        this.inputReader = inputReader;
    }
    @Override
    public Map<MenuRoutes, Menu> provideMenus() {
        return Map.of(
                MenuRoutes.MAIN, new MainMenu(MenuRoutes.MAIN),
                MenuRoutes.RANDOM, new RandomMenu(MenuRoutes.RANDOM, new RandomGnomeStrategy(), gnomes),
                MenuRoutes.MANUAL, new ManualMenu(MenuRoutes.MANUAL, new ManualGnomeStrategy(), gnomes, inputReader),
                MenuRoutes.FILE, new FileMenu(MenuRoutes.FILE, new FileGnomeStrategy(), gnomes)
        );
    }
}
