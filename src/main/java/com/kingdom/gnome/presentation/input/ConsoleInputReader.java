package com.kingdom.gnome.presentation.input;

import java.util.Scanner;

public class ConsoleInputReader {

    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInteger() {
        while (true) {
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число:");
            }
        }
    }
}