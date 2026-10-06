package com.kingdom.gnome.service;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.utils.CustomLinkedList;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;
import com.kingdom.gnome.presentation.strategy.GnomeCreationStrategy;
import com.kingdom.gnome.service.perform.fileStrategy.GnomeFileWriter;
import com.kingdom.gnome.service.perform.fileStrategy.SortService.GnomeSortService;
import com.kingdom.gnome.service.sorting.SortOption;

import java.util.Collections;
import java.util.List;

public class GnomeDataService {
    private final List<Gnome> gnomes = new CustomLinkedList<>();
    private final GnomeSortService sortService;
    private final GnomeFileWriter fileWriter;

    public GnomeDataService(GnomeSortService sortService, GnomeFileWriter fileWriter) {
        this.sortService = sortService;
        this.fileWriter = fileWriter;
    }

    public void generateGnomes(GnomeCreationStrategy strategy, ConsoleInputReader inputReader) {
        strategy.createGnomes(inputReader, gnomes);
    }

    public List<Gnome> getGnomes() {
        return gnomes;
    }

    public void sortAndSave(SortOption sortOption) {
        sortOption.apply(sortService, this.gnomes);
        fileWriter.appendGnomes(this.gnomes);
    }

    public boolean isEmpty() {
        return gnomes.isEmpty();
    }
}
