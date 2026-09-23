package com.kingdom.gnome.presentation.menu.providers;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.menu.entities.Menu;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class MenuProvider {
    protected int startIdx;
    protected final List<Gnome> gnomes;

    public MenuProvider(List<Gnome> gnomes) {
        this.gnomes = gnomes;
        this.startIdx = 0;
    }
    public MenuProvider(int startIdx, List<Gnome> gnomes) {
        this.gnomes = gnomes;
        this.startIdx = startIdx;
    }

    public abstract Map<MenuRoutes, Menu>  provideMenus ();
    public int getStartIdx() {
        return startIdx;
    }
}
