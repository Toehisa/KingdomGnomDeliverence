package com.example.sorting.input;

import com.example.sorting.collection.GnomeCollection;
import com.example.sorting.model.Gnome;

import java.util.Scanner;

public class ManualGnomeStrategy implements InputStrategy {

    private final Scanner scanner;

    public ManualInputStrategy(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public GnomeCollection read(int size) {

        GnomeCollection gnomes = new GnomeCollection();

        for (int i = 0; i < size; i++) {

            System.out.println("\nГном №" + (i + 1));

            String name = readName();
            String role = readRole();
            double height = readHeight();

            Gnome gnome = new Gnome.Builder()
                    .name(name)
                    .role(role)
                    .height(height)
                    .build();

            gnomes.add(gnome);
        }

        return gnomes;
    }
    private String readName() {

        while (true) {
            System.out.print("Введите имя гнома: ");

            String name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                return name;
            }

            System.out.println("Имя не может быть пустым.");
        }
    }
    private String readRole() {

        while (true) {
            System.out.print("Введите роль гнома: ");

            String role = scanner.nextLine().trim();

            if (!role.isEmpty()) {
                return role;
            }

            System.out.println("Роль не может быть пустой.");
        }
    }
    private double readHeight() {

        while (true) {
            System.out.print("Введите рост гнома в сантиметрах: ");

            String input = scanner.nextLine();

            try {
                double height = Double.parseDouble(input);

                if (height > 0 && height <= 300) {
                    return height;
                }

                System.out.println(
                        "Рост должен быть от 0 до 300 см."
                );

            } catch (NumberFormatException e) {
                System.out.println(
                        "Ошибка: введите число."
                );
            }
        }
    }
}