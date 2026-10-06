package com.kingdom.gnome.service;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.Arrays;
import java.util.List;

public class GnomePrintService {
    private final GnomeDataService data;

    public GnomePrintService(GnomeDataService data) {
        this.data = data;
    }

    public void showGnomes() {
        System.out.println("--- Список гномов ---");
        if (!data.isEmpty()) {
            data.getGnomes().forEach(System.out::println);
        } else {
            System.out.println("Армия пуста");
        }
        System.out.println("---------------------");
    }

    public void showMessage(String s) {
        System.out.println(s);
    }
}
