package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;

import java.util.List;

public class GnomesMenu extends Menu {
    private final List<Gnome> gnomes;

    public GnomesMenu(MenuRoutes routeID, List<Gnome> gnomes) {
        super(routeID);
        this.gnomes = gnomes;
    }

    @Override
    public void show() {
        System.out.println("--- Состав армии ---");
        if (gnomes.isEmpty()) {
            System.out.println("В RAM пока пусто.");
        } else {
            for (int i = 0; i < gnomes.size(); i++) {
                System.out.println((i + 1) + ". " + gnomes.get(i));
            }
        }
        System.out.println("--------------------");
    }

    @Override
    public MenuRoutes execute() {
        return MenuRoutes.MAIN;
    }
}