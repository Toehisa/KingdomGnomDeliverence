package com.kingdom.gnome.presentation.strategy;

import java.util.List;
import java.util.Scanner;
import com.kingdom.gnome.dao.entity.Gnome;

public interface GnomeCreationStrategy {
    List<Gnome> create(Scanner scanner);
}
