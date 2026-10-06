package com.kingdom.gnome.presentation.strategy;

import java.util.List;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.perform.GnomeFileReader;
import com.kingdom.gnome.service.perform.fileStrategy.GnomeImportResult;
import com.kingdom.gnome.service.perform.fileStrategy.GnomeImportService;
import com.kingdom.gnome.presentation.input.ConsoleInputReader;

public class FileGnomeStrategy implements GnomeCreationStrategy {

    private final GnomeImportService importService;

    public FileGnomeStrategy() {
        this.importService = new GnomeImportService(new GnomeFileReader());
    }

    @Override
    public void createGnomes(ConsoleInputReader inputReader, List<Gnome> gnomes) {
        System.out.println(" --- ВЫГРУЗКА ГНОМОВ ИЗ ФАЙЛА --- ");

        int count = inputReader.readPositiveInteger("Введите количество гномов для чтения из файла (0 для выхода): ");
        if (count == 0) {
            cancelMessage();
            return;
        }

        String filename = inputReader.readLine("Введите имя файла (или 0 для выхода): ");
        if (filename.trim().equals("0")) {
            cancelMessage();
            return;
        }

        GnomeImportResult result = importService.importGnomes(filename, count, gnomes);
        handleResult(result);
    }

    private static void cancelMessage() {
        System.out.println("Отмена операции.");
    }

    private void handleResult(GnomeImportResult result) {
        switch (result.getStatus()) {
            case SUCCESS -> System.out.printf("Загружено: %s гномов из файла", result.getRequestedCount());
            case PARTIAL -> System.out.printf("В файле только %s гномов.\nЗагружены все.\n", result.getGnomes().size());
            case FILE_NOT_FOUND -> System.out.printf("Ошибка: файл '%s' не найден\nПоместите файл в папку resources/ проекта", result.getFilename());
            case EMPTY_DATA -> System.out.println("Ошибка: в файле нет корректных данных");
        }
    }
}