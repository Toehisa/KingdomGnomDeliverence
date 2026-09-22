package com.kingdom.gnome.presentation.menu;

import com.sun.tools.javac.Main;

public class MainSelector extends MenuSelector{
    //List<Menu> menus;
    //int curMenuIndex = -1;

    public MainSelector() {super();}
    public MainSelector(int menuCount) {super(menuCount);}

    @Override
    Menu currentMenu() {
        return menus.get(curMenuIndex);
    }

    @Override
    void showMenu() {

    }

    @Override
    void selectMenu() {

    }

    @Override
    void switchMenu() {

    }
}
