package com.kingdom.gnome.presentation.strategy;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.GnomeNumberPromt;
import com.kingdom.gnome.dao.perform.GnomeFileReader;
import com.kingdom.gnome.service.perform.fileStrategy.GnomeImportResult;
import com.kingdom.gnome.service.perform.fileStrategy.GnomeImportService;
import javax.swing.*;


public class FileGnomeStrategy implements GnomeCreationStrategy {

    private final GnomeImportService importService;

    public FileGnomeStrategy() {
        this.importService = new GnomeImportService(new GnomeFileReader());
    }

    @Override
    public List<Gnome> create(Scanner scanner) {
        System.out.println(" --- ВЫГРУЗКА ГНОМОВ ИЗ ФАЙЛА --- ");

        int count = readCount(scanner);
        if (count == 0) {
            System.out.println("Отмена операции.");
            return null;
        }
        scanner.nextLine(); // Съедаем символ перехода на след. строку

        File file = getFile();
        if (file == null) {
            System.out.println("Отмена операции.");
            return null;
        }

        GnomeImportResult result = importService.importGnomes(file, count);

        return handleResult(result);
    }

    private int readCount(Scanner scanner) {
        GnomeNumberPromt numberPromt = new GnomeNumberPromt(
                "Введите количество гномов для чтения из файла (0 для выхода): ",
                scanner
        );
        return numberPromt.getCount();
    }

    private File getFile() {
        File file = null;
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        return file;
    }

    /*private String readFilename(Scanner scanner) {
        while (true) {
            System.out.println("Введите имя файла (или 0 для выхода)");
            System.out.print(">> ");
            String filename = scanner.nextLine().trim();

            if (filename.isEmpty()) {
                System.out.println("Ошибка: имя файла не может быть пустым.");
                continue;
            }
            if (filename.equals("0")) {
                return null;
            }
            return filename;
        }
    }*/

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