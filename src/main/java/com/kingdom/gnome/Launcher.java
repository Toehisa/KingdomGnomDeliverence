package com.kingdom.gnome;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.providers.MainMenuProvider;
import com.kingdom.gnome.presentation.menu.providers.MenuProvider;
import com.kingdom.gnome.presentation.menu.selectors.MainMenuSelector;
import com.kingdom.gnome.presentation.menu.selectors.MenuSelector;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Стартовая точка приложения
 *
 */
public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try (Scanner scanner = new Scanner(System.in)) {
                List<Gnome> gnomes = new ArrayList<>();
                MenuProvider menuProvider = new MainMenuProvider(gnomes);
                MenuSelector menuSelector = new MainMenuSelector(menuProvider);
                menuSelector.run(scanner);
            }
        });

    }
}
