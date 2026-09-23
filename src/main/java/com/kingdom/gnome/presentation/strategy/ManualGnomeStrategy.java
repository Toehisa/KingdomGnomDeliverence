package com.kingdom.gnome.presentation.strategy;


import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;

import java.util.List;
import java.util.Scanner;

public class ManualGnomeStrategy implements GnomeCreationStrategy {

    @Override
    public List<Gnome> create(Scanner scanner) {
        return List.of();
    }
}