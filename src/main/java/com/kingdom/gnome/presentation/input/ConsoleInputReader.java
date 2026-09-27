package com.kingdom.gnome.presentation.input;

import org.jspecify.annotations.NonNull;

import java.util.Scanner;
import java.util.function.Function;

public class ConsoleInputReader {

    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T> T readUntilValid(String prompt, @NonNull Function<String, T> function) {
        while (true) {
            try {
                System.out.print(prompt);
                return function.apply(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String readLine(String prompt) {
        return readUntilValid(prompt, input -> {
            if (!input.isEmpty()) {
                return input;
            }
            throw new IllegalArgumentException("Ошибка: поле не может быть пустым.");
        });
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

                    throw new IllegalArgumentException("Ошибка: введите число от " + min + " до " + max);
                }
        );
    }

    public int readPositiveInteger(String prompt) {
        return readUntilValid(
                prompt,
                (userInput) -> {
                    try {
                        int digit = Integer.parseInt(userInput);
                        if (digit >= 0) {
                            return digit;
                        }
                    } catch (NumberFormatException e){}

                    throw new IllegalArgumentException("Ошибка: введите положительное число!");
                }
        );
    }


    public String readLetters(String prompt) {
        return readUntilValid(prompt, userInput -> {
            if (userInput.matches("[а-яА-ЯёЁa-zA-Z ]+")) {
                return userInput;
            }
            throw new IllegalArgumentException("Ошибка: здесь можно вводить только буквы.");
        });
    }
}