package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.presentation.menu.selectors.MenuSelector;

import java.util.Scanner;

abstract public class Menu {
    protected final MenuRoutes routeID;
    public Menu(MenuRoutes routeID) {
        this.routeID = routeID;
    }
    public abstract void show();
    public abstract MenuRoutes execute(Scanner scanner);
}
