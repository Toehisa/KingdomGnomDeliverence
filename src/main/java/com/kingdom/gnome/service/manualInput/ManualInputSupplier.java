package com.kingdom.gnome.service.manualInput;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

import java.util.function.Supplier;

public class ManualInputSupplier implements Supplier<Gnome> {
    private ConsoleInputReader inputReader;

    public ManualInputSupplier(ConsoleInputReader inputReader) {
        this.inputReader = inputReader;
    }

    @Override
    public Gnome get() {
        String name = inputReader.readLetters("Введите имя: ").trim();
        System.out.println("Выберите роль:");
        GnomeRole[] roles = GnomeRole.values();
        for (int j = 0; j < roles.length; j++) {
            System.out.printf("%d. %s%n", j + 1, roles[j].getTitle());
        }

        int roleNumber;
        while (true) {
            System.out.print("Введите номер роли: ");
            String input = inputReader.readLine("").trim();
            try {
                roleNumber = Integer.parseInt(input);
                if (roleNumber < 1 || roleNumber > roles.length) {
                    System.out.println(
                            "Ошибка: выберите номер роли из списка."
                    );
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(
                        "Ошибка: введите номер роли цифрами."
                );
            }
        }

        GnomeRole role = roles[roleNumber - 1];
        Gnome gnome = Gnome.builder()
                .name(name)
                .role(role)
                .build();
        System.out.println("Гном успешно создан!");
        return gnome;
    }
}
