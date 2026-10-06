package com.kingdom.gnome.presentation.menu.providers.menu;

import com.kingdom.gnome.service.GnomeDataService;
import com.kingdom.gnome.service.GnomePrintService;
import com.kingdom.gnome.service.counting.GnomeCounterService;
import com.kingdom.gnome.presentation.menu.entities.*;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.FileGnomeStrategy;
import com.kingdom.gnome.presentation.strategy.ManualGnomeStrategy;
import com.kingdom.gnome.presentation.strategy.RandomGnomeStrategy;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import static com.kingdom.gnome.presentation.menu.routes.MenuRoutes.*;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class MainMenuProvider extends MenuProvider {

    private final ConsoleInputReader inputReader;
    private final GnomeCounterService counterService;

    public MainMenuProvider(GnomeDataService dataService, ConsoleInputReader inputReader, GnomePrintService printService, ForkJoinPool pool) {
        super(dataService, printService);
        this.inputReader = inputReader;
        this.counterService = new GnomeCounterService(pool);
    }
    @Override
    public Map<MenuRoutes, Menu> provideMenus() {
        return Map.of(
                MAIN, new MainMenu(MAIN, inputReader),
                RANDOM, new RandomMenu(RANDOM, new RandomGnomeStrategy(), dataService, printService, inputReader),
                MANUAL, new ManualMenu(MANUAL, new ManualGnomeStrategy(), dataService, printService, inputReader),
                FILE, new FileMenu(FILE, new FileGnomeStrategy(), dataService, printService, inputReader),
                SORT, new SortMenu(SORT, dataService, printService, inputReader),
                GNOMES, new GnomesMenu(GNOMES, dataService, printService),
                SEARCH, new SearchMenu(SEARCH, counterService, dataService, inputReader),
                EXIT, new ExitMenu()
        );
    }
}
