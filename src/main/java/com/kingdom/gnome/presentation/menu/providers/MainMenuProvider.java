package com.kingdom.gnome.presentation.menu.providers;

import com.kingdom.gnome.service.counting.GnomeCounterService;
import com.kingdom.gnome.service.perform.fileStrategy.GnomeFileWriter;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.entities.*;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.strategy.FileGnomeStrategy;
import com.kingdom.gnome.presentation.strategy.ManualGnomeStrategy;
import com.kingdom.gnome.presentation.strategy.RandomGnomeStrategy;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.service.perform.fileStrategy.SortService.GnomeSortService;
import static com.kingdom.gnome.presentation.menu.routes.MenuRoutes.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class MainMenuProvider extends MenuProvider {

    private final ConsoleInputReader inputReader;
    private final GnomeCounterService counterService;

    public MainMenuProvider(List<Gnome> gnomes, ConsoleInputReader inputReader, ExecutorService threadSupplier) {
        super(gnomes);
        this.inputReader = inputReader;
        this.counterService = new GnomeCounterService(threadSupplier);
    }
    @Override
    public Map<MenuRoutes, Menu> provideMenus() {
        return Map.of(
                MAIN, new MainMenu(MAIN, inputReader),
                RANDOM, new RandomMenu(RANDOM, new RandomGnomeStrategy(), gnomes, inputReader),
                MANUAL, new ManualMenu(MANUAL, new ManualGnomeStrategy(), gnomes, inputReader),
                FILE, new FileMenu(FILE, new FileGnomeStrategy(), gnomes, inputReader),
                SORT, new SortMenu(SORT, new GnomeSortService(), gnomes, inputReader, new GnomeFileWriter("sorted_gnomes.txt")),
                GNOMES, new GnomesMenu(GNOMES, gnomes),
                SEARCH, new SearchMenu(SEARCH, counterService, gnomes, inputReader),
                EXIT, new ExitMenu()
        );
    }
}
