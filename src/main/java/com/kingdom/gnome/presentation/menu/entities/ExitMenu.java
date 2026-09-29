package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import static com.kingdom.gnome.presentation.menu.routes.MenuRoutes.*;

public class ExitMenu extends Menu{
    public ExitMenu() {
        super(null);
    }

    @Override
    public void show() {
        System.out.println("Звук трамбона папочки гоблина");
    }

    @Override
    public MenuRoutes execute() {
        return null;
    }
}
