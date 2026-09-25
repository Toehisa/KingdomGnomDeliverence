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
        return inputReader.readPositiveInteger(promptMsg);
    }
}
