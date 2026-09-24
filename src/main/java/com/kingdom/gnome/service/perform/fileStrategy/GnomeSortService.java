package com.kingdom.gnome.service.perform.fileStrategy;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.Comparator;
import java.util.List;

public class GnomeSortService {

    public void sortByName(List<Gnome> gnomes) {
        gnomes.sort(
                Comparator.comparing(Gnome::getName)
        );
    }
    public void sortByRole(List<Gnome> gnomes) {
        gnomes.sort(
                Comparator.comparing(
                        gnome -> gnome.getRole().getTitle()
                )
        );
    }
