package com.kingdom.gnome.service.sorting;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.service.perform.fileStrategy.SortService.GnomeSortService;

import java.util.List;
import java.util.function.BiConsumer;

public enum SortOption {
    BY_NAME("По имени", GnomeSortService::sortByName),
    BY_ROLE("По роли", GnomeSortService::sortByRole),
    BY_NAME_AND_ROLE("По имени и роли", GnomeSortService::sortByNameAndRole),
    BY_STAMINA_EVENS("По стамине, оставляя нечетные на месте", GnomeSortService::sortByStaminaEvensOnly);

    private final String label;
    public final BiConsumer<GnomeSortService, List<Gnome>> action;

    SortOption(String label, BiConsumer<GnomeSortService, List<Gnome>> action) {
        this.label = label;
        this.action = action;
    }

    public String getLabel() {
        return label;
    }

    public void apply(GnomeSortService sortService, List<Gnome> gnomes) {
        this.action.accept(sortService, gnomes);
    }
}
