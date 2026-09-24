package com.kingdom.gnome.presentation;

import com.kingdom.gnome.presentation.input.ConsoleInputReader;

public class GnomeNumberPromt {
    private final String promptMsg;
    private final ConsoleInputReader inputReader;

    public GnomeNumberPromt(String promptMsg, ConsoleInputReader inputReader) {
        this.promptMsg = promptMsg;
        this.inputReader = inputReader;
    }

    public int getCount() {
        while(true) {
            System.out.println(promptMsg);

            try {
                int count = inputReader.readInteger();

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
