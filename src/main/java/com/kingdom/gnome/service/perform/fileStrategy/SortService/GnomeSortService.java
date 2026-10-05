package com.kingdom.gnome.service.perform.fileStrategy.SortService;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.utils.CustomLinkedList;

import java.util.*;

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

    public void sortByStaminaEvensOnly(List<Gnome> gnomes ) {
        List<Gnome> evens = new CustomLinkedList<>();
        for(Gnome gnome : gnomes) {
            if(gnome.getRole().getBaseStamina() % 2 == 0){
                evens.add(gnome);
            }
        }

        evens.sort(Gnome::compareByStamina);

        int evenIndex = 0;
        for (int i = 0; i < gnomes.size(); i++) {
            if(gnomes.get(i).getRole().getBaseStamina() % 2 == 0) {
                gnomes.set(i, evens.get(evenIndex++));
            }
        }
    }
}