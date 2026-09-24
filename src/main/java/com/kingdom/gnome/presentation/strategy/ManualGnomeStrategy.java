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

            System.out.println("\nГном №" + (i + 1));

            System.out.print("Введите имя гнома: ");
            String name = scanner.nextLine().trim();

            System.out.println("Выберите роль:");

            GnomeRole[] roles = GnomeRole.values();

            for (int j = 0; j < roles.length; j++) {
                System.out.println(
                        (j + 1) + ". " + roles[j].getTitle()
                );
            }