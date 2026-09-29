package com.kingdom.gnome.service.perform.fileStrategy;

import com.kingdom.gnome.dao.entity.Gnome;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GnomeFileWriter {

    private final String fileName;

    public GnomeFileWriter(String fileName) {
        this.fileName = fileName;
    }

    public void appendGnomes(List<Gnome> gnomes) {
        try (FileWriter writer = new FileWriter(fileName, true)) {

            writer.write("\n--- Отсортированные гномы ---\n");

            gnomes.stream()
                    .map(Gnome::toString)
                    .forEach(text -> {
                        try {
                            writer.write(text);
                            writer.write(System.lineSeparator());
                        } catch (IOException e) {
                            throw new RuntimeException("Ошибка записи в файл", e);
                        }
                    });

            writer.write("--- Конец списка ---\n");

        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}