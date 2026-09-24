package com.kingdom.gnome.presentation.strategy;


import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;

import java.util.List;
import java.util.Scanner;

public class ManualGnomeStrategy implements GnomeCreationStrategy {

    @Override
    public List<Gnome> create(Scanner scanner) {

        List<Gnome> gnomes = new java.util.ArrayList<>();

        System.out.print("Введите количество гномов: ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < count; i++) {

            System.out.printf("\nГном №%d%n", i + 1);

            String name = readName(scanner);

            System.out.println("Выберите роль:");

            GnomeRole[] roles = GnomeRole.values();

            for (int j = 0; j < roles.length; j++) {
                System.out.printf("%d. %s%n", j + 1, roles[j].getTitle());
            }
            GnomeRole role;

            while (true) {

                System.out.print("Ваш выбор: ");

                try {
                    int choice = Integer.parseInt(
                            scanner.nextLine()
                    );

                    if (choice >= 1 && choice <= roles.length) {
                        role = roles[choice - 1];
                        break;
                    }

                    System.out.printf("Выберите число от 1 до %d%n", roles.length);

                } catch (NumberFormatException e) {
                    System.out.println(
                            "Введите номер роли."
                    );
                }
            }

            Gnome gnome = Gnome.builder()
                    .name(name)
                    .role(role)
                    .build();

            gnomes.add(gnome);
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
};

