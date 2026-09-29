package com.kingdom.gnome.presentation.menu.selectors;

import com.kingdom.gnome.presentation.menu.entities.Menu;
import com.kingdom.gnome.presentation.menu.providers.MenuProvider;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;

import java.util.Scanner;

public class MainMenuSelector extends MenuSelector {
    public MainMenuSelector() {
        super();
    }
    public MainMenuSelector(MenuProvider provider) {
        super(provider);
        cursor = MenuRoutes.MAIN;
    }

    @Override
    protected void showMenu() {
        currentMenu().show();
    }

    @Override
    protected void switchMenu(MenuRoutes route) {
        Menu target = menus.get(route);

        if(target != null && !target.canEnter()){
            System.out.println(target.denyMessage());
            return;
        }

        cursor = route;
    }

    @Override
    protected Menu currentMenu() {
        return menus.get(cursor);
    }


    @Override
    public void run(Scanner scanner) {
        do {
            showMenu();
            var route = currentMenu().execute();
            switchMenu(route);
        } while (!cursorIsExit());
    }

    private boolean cursorIsExit() {
        return cursor == MenuRoutes.EXIT;
    }
}
