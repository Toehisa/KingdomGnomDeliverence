package com.kingdom.gnome.service.perform.fileStrategy;

import java.io.File;
import java.util.List;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.perform.GnomeFileReader;

public class GnomeImportService {

    private final GnomeFileReader fileReader;

    public GnomeImportService(GnomeFileReader fileReader) {
        this.fileReader = fileReader;
    }

    public GnomeImportResult importGnomes(File file, int count) {
        //File file = fileReader.findFile(filename);

        if (file == null || !file.exists()) {
            return GnomeImportResult.fileNotFound(count, file);
        }

        List<Gnome> gnomes = fileReader.readGnomesFromFile(file, count);

        if (gnomes.isEmpty()) {
            return GnomeImportResult.emptyData(count, file);
        }

        if (gnomes.size() >= count) {
            return GnomeImportResult.success(gnomes, count, file);
        }

        return GnomeImportResult.partial(gnomes, count, file);
    }
}