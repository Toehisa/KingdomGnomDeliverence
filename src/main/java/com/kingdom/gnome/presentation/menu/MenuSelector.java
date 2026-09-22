package com.kingdom.gnome.presentation.menu;

import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;

abstract public class MenuSelector {
    List<Menu> menus;
    int curMenuIndex = -1;

    MenuSelector() {
        menus = new ArrayList<>();
    }
    MenuSelector(int menuCount) {
        menus = new ArrayList<>(menuCount);
    }

    abstract Main currentMenu();
    abstract void showMenu();
    abstract void selectMenu();
    abstract void switchMenu();
}
