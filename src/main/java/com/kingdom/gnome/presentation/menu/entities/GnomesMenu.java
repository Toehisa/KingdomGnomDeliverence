package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.service.GnomeDataService;
import com.kingdom.gnome.service.GnomePrintService;

import java.util.List;

public class GnomesMenu extends Menu {
    private final GnomeDataService dataService;
    private final GnomePrintService printService;

    public GnomesMenu(MenuRoutes routeID, GnomeDataService dataService, GnomePrintService printService) {
        super(routeID);
        this.dataService = dataService;
        this.printService = printService;
    }

    @Override
    public boolean canEnter() {
        return !dataService.isEmpty();
    }

    @Override
    public String denyMessage() {
        return "Армия пуста! Сначала создайте гномов.";
    }

    @Override
    public void show() {
        printService.showGnomes();
    }

    @Override
    public MenuRoutes execute() {
        return MenuRoutes.MAIN;
    }
}