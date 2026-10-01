package com.kingdom.gnome;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.providers.MainMenuProvider;
import com.kingdom.gnome.presentation.menu.providers.MenuProvider;
import com.kingdom.gnome.presentation.menu.selectors.MainMenuSelector;
import com.kingdom.gnome.presentation.menu.selectors.MenuSelector;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.service.counting.ThreadSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Стартовая точка приложения
 * */
public class Launcher {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)){
            List<Gnome> gnomes = new ArrayList<>();
            ConsoleInputReader inputReader = new ConsoleInputReader(scanner);
            ThreadSupplier threadSupplier = new ThreadSupplier();
            MenuProvider menuProvider = new MainMenuProvider(gnomes,inputReader, threadSupplier);
            MenuSelector menuSelector = new MainMenuSelector(menuProvider);
            menuSelector.run(scanner);
        }
    }
}
