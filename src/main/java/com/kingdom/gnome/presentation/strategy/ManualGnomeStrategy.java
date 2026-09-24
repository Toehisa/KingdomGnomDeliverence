package com.kingdom.gnome.presentation.strategy;


import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;

import java.util.List;
import java.util.Scanner;

public class ManualGnomeStrategy implements GnomeCreationStrategy {

    @Override
    public List<Gnome> create(Scanner scanner) {

        List<Gnome> gnomes = new java.util.ArrayList<>();

        int count;

        while (true) {

            System.out.print("Введите количество выдуваемых жидких гномов: ");

            String input = scanner.nextLine().trim();

            try {
                count = Integer.parseInt(input);

                if (count <= 0) {
                    System.out.println("Количество выдуваемых жидких гномов должно быть больше нуля.");
                    continue;
                }

                break;

            } catch (NumberFormatException e) {
                System.out.println("Введите количество выдуваемых жидких гномов цифрами.");
            }
        }

        // Создание гномов
        for (int i = 0; i < count; i++) {

            System.out.printf("%nГном №%d%n", i + 1);

            String name = readName(scanner);

            System.out.println("Выберите роль:");

            GnomeRole[] roles = GnomeRole.values();

            for (int j = 0; j < roles.length; j++) {
                System.out.printf("%d. %s%n", j + 1, roles[j].getTitle());
            }

            int roleNumber;

            while (true) {

                System.out.print("Введите номер роли: ");

                String input = scanner.nextLine().trim();

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

            gnomes.add(gnome);

            System.out.println("Гном успешно создан!");
        }

        return gnomes;
    }
    private String readName(Scanner scanner) {

        while (true) {

            System.out.print("Введите имя гнома: ");

            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(
                        "Имя не может быть пустым. " +
                                "Пожалуйста, укажите имя гнома."
                );
                continue;
            }
            if (!name.matches("[a-zA-Zа-яА-ЯёЁ ]+")) {
                System.out.println(
                        "Имя может содержать только буквы и пробелы."
                );
                continue;
            }
            return name;
        }
    }
}

