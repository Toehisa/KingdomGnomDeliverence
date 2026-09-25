package com.kingdom.gnome.presentation.input;

import java.util.Scanner;

public class ConsoleInputReader {

    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readLine(String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Ошибка: поле не может быть пустым.");
        }
    }

    public int readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    public int readPositiveInteger(String prompt) {
        while (true) {
            int number = readInteger(prompt);

            if (number > 0) {
                return number;
            }

            System.out.println("Ошибка: число не может быть отрицательным.");
        }
    }


    public String readLetters(String prompt) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Ошибка: поле не может быть пустым.");
                continue;
            }

            if (input.matches("[а-яА-ЯёЁa-zA-Z ]+")) {
                return input;
            }

            System.out.println(
                    "Ошибка: здесь можно вводить только буквы."
            );
        }
    }
}