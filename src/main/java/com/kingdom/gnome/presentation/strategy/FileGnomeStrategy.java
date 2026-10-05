package com.kingdom.gnome.presentation.strategy;

import java.util.List;

import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.GnomeNumberPromt;
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
    public List<Gnome> create(ConsoleInputReader inputReader) {
        System.out.println(" --- ВЫГРУЗКА ГНОМОВ ИЗ ФАЙЛА --- ");

        int count = inputReader.readPositiveInteger("Введите количество гномов для чтения из файла (0 для выхода): ");
        if (count == 0) {
            cancelMessage();
            return null;
        }

        String filename = inputReader.readLine("Введите имя файла (или 0 для выхода): ");
        if (filename.trim().equals("0")) {
            cancelMessage();
            return null;
        }

        GnomeImportResult result = importService.importGnomes(filename, count);

        return handleResult(result);
    }

    private static void cancelMessage() {
        System.out.println("Отмена операции.");
    }

    private List<Gnome> handleResult(GnomeImportResult result) {
        switch (result.getStatus()) {
            case SUCCESS:
                System.out.println("Загружено: " + result.getRequestedCount() + " гномов из файла");
                return result.getGnomes();

            case PARTIAL:
                System.out.println("В файле только " + result.getGnomes().size()
                        + " гномов. Загружены все.");
                return result.getGnomes();

            case FILE_NOT_FOUND:
                System.out.println("Ошибка: файл '" + result.getFilename() + "' не найден");
                System.out.println("Поместите файл в папку resources/ проекта");
                return null;

            case EMPTY_DATA:
                System.out.println("Ошибка: в файле нет корректных данных");
                return null;

            default:
                return null;
        }
    }
}