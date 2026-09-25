package com.kingdom.gnome.presentation.input;

import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;

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

    public int readInteger(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println(
                        "Вводи число которое указано, лишнего не придумывай, от " + min + " до " + max + "."
                );

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    public int readPositiveInteger(String prompt) {
        while (true) {
            int number = readInteger(prompt,1,MAX_VALUE);

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