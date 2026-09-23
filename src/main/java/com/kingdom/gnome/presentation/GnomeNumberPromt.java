package com.kingdom.gnome.presentation;

import java.util.Scanner;

public class GnomeNumberPromt {
    private final String promptMsg;
    private final Scanner scanner;

    public GnomeNumberPromt(String promptMsg, Scanner scanner) {
        this.promptMsg = promptMsg;
        this.scanner = scanner;
    }

    public int getCount() {
        while(true) {
            System.out.println(promptMsg);

            try {
                int count = scanner.nextInt();

                if(count >= 0){
                    return count;
                } else {
                    System.out.println("Ошибка: число должно быть неотрицательным");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число");
            }
        }
    }
}
