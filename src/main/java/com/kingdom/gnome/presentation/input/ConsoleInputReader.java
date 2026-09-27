package com.kingdom.gnome.presentation.input;

import org.jspecify.annotations.NonNull;

import java.util.Scanner;
import java.util.function.Function;

import static java.lang.Integer.MAX_VALUE;

public class ConsoleInputReader {

    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T> T readUntilValid(String prompt, @NonNull Function<String, T> function) {
        while (true) {
            try {
                System.out.println(prompt);
                return function.apply(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
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

    public int readIntInRange(String prompt, int min, int max) {
        return readUntilValid(
                prompt,
                (userInput) -> {
                    try {
                        int digit = Integer.parseInt(userInput);
                        if (digit >= min && digit <= max) {
                            return digit;
                        }
                    } catch (NumberFormatException e){}

                    throw new IllegalArgumentException("Пункт меню не найден, попробуйте еще раз");
                }
        );
    }

    public int readPositiveInteger(String prompt) {
        while (true) {
            int number = readIntInRange(prompt,1,MAX_VALUE);

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