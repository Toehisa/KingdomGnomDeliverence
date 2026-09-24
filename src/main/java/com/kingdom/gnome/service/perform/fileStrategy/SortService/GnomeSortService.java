package com.kingdom.gnome.service.perform.fileStrategy.SortService;

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

    public void sortByNameAndRole(List<Gnome> gnomes) {
        gnomes.sort(
                Comparator.comparing(Gnome::getName)
                        .thenComparing(
                                gnome -> gnome.getRole().getTitle()
                        )
        );
    }

    public void sort(List<Gnome> gnomes, int choice) {

        switch (choice) {

            case 1:
                sortByName(gnomes);
                break;

            case 2:
                sortByRole(gnomes);
                break;

            case 3:
                sortByNameAndRole(gnomes);
                break;

            default:
                throw new IllegalArgumentException(
                        "Неизвестный вариант сортировки"
                );
        }
    }
}