package com.kingdom.gnome.presentation.menu.selectors;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.entities.Menu;
import com.kingdom.gnome.presentation.menu.providers.MenuProvider;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;

import java.util.*;

abstract public class MenuSelector {
    final Map<MenuRoutes, Menu> menus;
    boolean isRunnable = true;
    MenuRoutes cursor;

    MenuSelector() {
        menus = new HashMap<>();
    }
    MenuSelector(MenuProvider provider) {
        menus = provider.provideMenus();
    }

    abstract protected void showMenu();
    abstract protected void switchMenu(MenuRoutes route);
    abstract protected Menu currentMenu();
    abstract public void run(Scanner scanner);
}
