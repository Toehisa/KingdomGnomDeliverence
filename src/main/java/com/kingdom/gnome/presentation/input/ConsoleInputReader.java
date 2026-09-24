package com.kingdom.gnome.presentation.input;

import java.util.Scanner;

public class ConsoleInputReader {

    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ты мне вводи цифры, а не буквы.");
                System.out.print("Твой выбор: ");
            }
        }
    }

    public String readString() {
        return scanner.nextLine().trim();
    }
}