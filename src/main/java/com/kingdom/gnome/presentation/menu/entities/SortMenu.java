package com.kingdom.gnome.presentation.menu.entities;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.presentation.menu.routes.MenuRoutes;
import com.kingdom.gnome.service.perform.fileStrategy.SortService.GnomeSortService;

import java.util.List;
import java.util.function.BiConsumer;

public class SortMenu extends Menu {
    private enum SortOption {
        BY_NAME("По величанию", GnomeSortService::sortByName),
        BY_ROLE("По иерархии", GnomeSortService::sortByRole),
        BY_NAME_AND_ROLE("По величанию и иерархии", GnomeSortService::sortByNameAndRole),
        BY_STAMINA_EVENS("По стамине, оставляя нечетные на месте", GnomeSortService::sortByStaminaEvensOnly);

        private final String label;
        private final BiConsumer<GnomeSortService, List<Gnome>> action;

        SortOption(String label, BiConsumer<GnomeSortService, List<Gnome>> action) {
            this.label = label;
            this.action = action;
        }
    }

    private static final SortOption[] OPTIONS = SortOption.values();
    private static final int BACK = OPTIONS.length + 1;

    private final List<Gnome> gnomes;
    private final ConsoleInputReader inputReader;
    private final GnomeSortService sortService;

    public SortMenu(MenuRoutes routeID, GnomeSortService sortService, List<Gnome> gnomes, ConsoleInputReader inputReader) {
        super(routeID);
        this.gnomes = gnomes;
        this.inputReader = inputReader;
        this.sortService = sortService;
    }

    @Override
    public boolean canEnter() {
        return !gnomes.isEmpty();
    }

    @Override
    public String denyMessage() {
        return "Армия пуста! Сначала создайте гномов.";
    }

    @Override
    public void show() {
        System.out.println("\n--- Сортировка орды гномов ---");
        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.println((i + 1) + ". " + OPTIONS[i].label);
        }
        System.out.println(BACK + ". Вернуться на главную");
    }

    @Override
    public MenuRoutes execute() {
        int num = inputReader.readIntInRange("Твой ответ: ", 1, BACK);

        if (num == BACK) {
            return MenuRoutes.MAIN;
        }

        OPTIONS[num - 1]
                .action
                .accept(sortService, gnomes);
        System.out.println("Гномы успешно отсортированы");
        return MenuRoutes.SORT;
    }
}
