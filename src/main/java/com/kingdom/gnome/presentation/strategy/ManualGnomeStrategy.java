package com.kingdom.gnome.presentation.strategy;


import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.service.manualInput.ManualInputSupplier;

import java.util.List;
import java.util.stream.Stream;

public class ManualGnomeStrategy implements GnomeCreationStrategy {

    @Override
    public List<Gnome> create(ConsoleInputReader inputReader) {

        int quantity;
        while (true) {
            System.out.print("Введите количество выдуваемых жидких гномов: ");
            String input = inputReader.readLine("").trim();
            try {
                quantity = Integer.parseInt(input);
                if (quantity <= 0) {
                    System.out.println("Количество выдуваемых жидких гномов должно быть больше нуля.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Введите количество выдуваемых жидких гномов цифрами.");
            }
        }

        List<Gnome> gnomeList = Stream.generate(new ManualInputSupplier(inputReader))
                .limit(quantity)
                .toList();

        return gnomeList;
    }
}