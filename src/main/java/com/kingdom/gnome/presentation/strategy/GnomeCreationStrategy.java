package com.kingdom.gnome.presentation.strategy;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import java.util.List;

import com.kingdom.gnome.dao.entity.Gnome;

public interface GnomeCreationStrategy {
    void createGnomes(ConsoleInputReader inputReader, List<Gnome> gnomes);
}
