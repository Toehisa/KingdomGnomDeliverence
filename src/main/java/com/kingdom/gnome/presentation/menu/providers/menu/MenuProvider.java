package com.kingdom.gnome.presentation.menu.providers.menu;

import com.kingdom.gnome.presentation.menu.entities.Menu;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.service.GnomeDataService;
import com.kingdom.gnome.service.GnomePrintService;

import java.util.Map;

abstract public class MenuProvider {
    protected int startIdx;
    protected final GnomeDataService dataService;
    protected final GnomePrintService printService;

    public MenuProvider(GnomeDataService dataService, GnomePrintService printService) {
        this.dataService = dataService;
        this.printService = printService;
        this.startIdx = 0;
    }
    public MenuProvider(int startIdx, GnomeDataService dataService, GnomePrintService printService) {
        this.dataService = dataService;
        this.printService = printService;
        this.startIdx = startIdx;
    }

    public abstract Map<MenuRoutes, Menu>  provideMenus ();
    public int getStartIdx() {
        return startIdx;
    }
}
