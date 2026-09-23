package com.kingdom.gnome;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.strategy.FileGnomeStrategy;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;
import com.kingdom.gnome.presentation.strategy.ManualGnomeStrategy;
import com.kingdom.gnome.presentation.strategy.RandomGnomeStrategy;

import java.util.List;
import java.util.Scanner;

/**
 * Стартовая точка приложения
 * */
public class Launcher {
    public static void main(String[] args) {
        //Пока так, потом архитектурно переделаем
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("1) Рандомно сгенерить gomogomoгнома");
            System.out.println("2) Создать гнома шелудивыми ручками");
            System.out.println("3) Выгрузить жидкого терминатора из файла");
            System.out.println("Нажмите нужную цифру:");

            GnomeCreationStrategy strategy = prostoGnomikStrategySelector(scanner);
            List<Gnome> gnomes = strategy.create(scanner);

            System.out.println(gnomes);

        }
    }

    // пока от балды, на первый раз
    static GnomeCreationStrategy prostoGnomikStrategySelector(Scanner scanner) {
        int choice = Integer.parseInt(scanner.nextLine().trim());

        return switch (choice) {
            case 1 -> new RandomGnomeStrategy();
            case 2 -> new ManualGnomeStrategy();
            case 3 -> new FileGnomeStrategy();
            default -> throw new RuntimeException("Big RuntimeGnomik problem");
        };
    }
    //Изменять можно только локально не добавляя файл в комиты!
    //в комиты идут только те файлы над которыми вы работали.
}
