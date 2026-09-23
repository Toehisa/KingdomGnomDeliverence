package com.kingdom.gnome.presentation;

import java.util.Scanner;

public class GnomeNumberPromt {
    private final String promptMsg;
    private final Scanner scanner;

    public GnomeNumberPromt(String promptMsg, Scanner scanner) {
        this.promptMsg = promptMsg;
        this.scanner = scanner;
    }

//    public GnomeNumberPromt(String promptMsg) {
//        this(promptMsg, new Scanner(System.in));
//    }

    public int getCount() {
        while(true) {
            System.out.println(promptMsg);
            String input = scanner.nextLine().trim();

            try {
                int count = Integer.parseInt(input);

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
