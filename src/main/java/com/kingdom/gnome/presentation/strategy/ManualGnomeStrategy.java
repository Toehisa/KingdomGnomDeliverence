package com.kingdom.gnome.presentation.strategy;


import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.service.manualInput.ManualInputSupplier;

import java.util.List;
import java.util.stream.Stream;

public class ManualGnomeStrategy implements GnomeCreationStrategy {

    @Override
    public void createGnomes(ConsoleInputReader inputReader, List<Gnome> gnomes) {

        int quantity = inputReader.readIntInRange("Введите количество выдуваемых жидких гномов: ", 1, 10);

        Stream.generate(new ManualInputSupplier(inputReader))
                .limit(quantity)
                .forEach(gnomes::add);
    }
}