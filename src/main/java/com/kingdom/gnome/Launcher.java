package com.kingdom.gnome;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.providers.MainMenuProvider;
import com.kingdom.gnome.presentation.menu.providers.MenuProvider;
import com.kingdom.gnome.presentation.menu.selectors.MainMenuSelector;
import com.kingdom.gnome.presentation.menu.selectors.MenuSelector;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

/**
 * Стартовая точка приложения
 * */
public class Launcher {
    public static void main(String[] args) {
        ForkJoinPool additionalThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        try (Scanner scanner = new Scanner(System.in)){
            List<Gnome> gnomes = new ArrayList<>();
            ConsoleInputReader inputReader = new ConsoleInputReader(scanner);
            MenuProvider menuProvider = new MainMenuProvider(gnomes,inputReader, additionalThreadPool);
            MenuSelector menuSelector = new MainMenuSelector(menuProvider);
            menuSelector.run(scanner);
        } finally {
            additionalThreadPool.shutdown();
        }
    }
}
