package com.kingdom.gnome.presentation.input;

import java.util.Scanner;

public class ConsoleInputReader {
    public int readInt(Scanner scanner) {
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
}