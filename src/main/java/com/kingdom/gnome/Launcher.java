package com.kingdom.gnome;

import com.kingdom.gnome.presentation.menu.providers.menu.MainMenuProvider;
import com.kingdom.gnome.presentation.menu.providers.menu.MenuProvider;
import com.kingdom.gnome.presentation.menu.selectors.MainMenuSelector;
import com.kingdom.gnome.presentation.menu.selectors.MenuSelector;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.service.GnomeDataService;
import com.kingdom.gnome.service.GnomePrintService;
import com.kingdom.gnome.service.perform.fileStrategy.GnomeFileWriter;
import com.kingdom.gnome.service.perform.fileStrategy.SortService.GnomeSortService;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

/**
 * Стартовая точка приложения
 * */
public class Launcher {
    public static void main(String[] args) {
        ForkJoinPool additionalThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        try (Scanner scanner = new Scanner(System.in)){
            MenuProvider menuProvider = buildMenuProvider(scanner, additionalThreadPool);
            MenuSelector menuSelector = new MainMenuSelector(menuProvider);
            menuSelector.run(scanner);
        } finally {
            additionalThreadPool.shutdown();
        }
    }

    private static MenuProvider buildMenuProvider(Scanner scanner, ForkJoinPool additionalThreadPool) {
        GnomeSortService sortService = new GnomeSortService();
        GnomeFileWriter fileWriter = new GnomeFileWriter("sorted_gnomes.txt");
        GnomeDataService dataService = new GnomeDataService(sortService, fileWriter);
        GnomePrintService printService = new GnomePrintService(dataService);
        ConsoleInputReader inputReader = new ConsoleInputReader(scanner);
        return new MainMenuProvider(dataService, inputReader, printService, additionalThreadPool);
    }
}
