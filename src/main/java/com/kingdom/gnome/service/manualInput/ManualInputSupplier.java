package com.kingdom.gnome.service.manualInput;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import java.util.function.Supplier;

public class ManualInputSupplier implements Supplier<Gnome> {
    private final ConsoleInputReader inputReader;

    public ManualInputSupplier(ConsoleInputReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public Gnome get() {
        String name = inputReader.readLetters("Введите имя гнома: ").trim();

        System.out.println("\n--- Выберите роль гнома ---");
        GnomeRole[] roles = GnomeRole.values();

        for (int i = 0; i < roles.length; i++) {
            System.out.printf("%d. %s%n", i + 1, roles[i].getTitle());
        }

        int roleNumber = inputReader.readIntInRange("Введите номер роли: ", 1, roles.length);
        GnomeRole role = roles[roleNumber - 1];

        return Gnome.builder()
                .name(name)
                .role(role)
                .build();
    }
}

