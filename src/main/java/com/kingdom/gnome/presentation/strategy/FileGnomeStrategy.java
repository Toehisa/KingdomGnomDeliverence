package com.kingdom.gnome.presentation.strategy;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.presentation.GnomeNumberPromt;
import com.kingdom.gnome.dao.perform.GnomeFileReader;

public class FileGnomeStrategy implements GnomeCreationStrategy{
    @Override
    public List<Gnome> create(Scanner scanner) {
        System.out.println(" --- ВЫГРУЗКА ГНОМОВ ИЗ ФАЙЛА --- ");

        GnomeNumberPromt numberPromt = new GnomeNumberPromt(
                "Введите количество гномов для чтения из файла (0 для выхода): ",
                scanner
        );

        int count = numberPromt.getCount();
        if(count == 0 ){
            System.out.println("Отмена операции.");
            return null;
        }

        String filename;
        while (true) {
            System.out.println("Введите имя файла (или 0 для выхода)");
            System.out.print(">> ");
            //Scanner scanner = new Scanner(System.in);
            filename = scanner.nextLine().trim();
            if(filename.isEmpty()) {
                System.out.println("Ошибка: имя файла не может быть пустым.");
                continue;
            }
            break;
        }
        if (filename.equals("0")){
            System.out.println("Отмена операции.");
            return null;
        }

        GnomeFileReader fileReader = new GnomeFileReader();
        File file = fileReader.findFile(filename);

        if(file == null || !file.exists()) {
            System.out.println("Ошибка: файл '" + filename + "' не найден");
            System.out.println("Поместите файл в папку resources/ проекта");
            return null;
        }

        List<Gnome> gnomes = fileReader.readGnomesFromFile(file, count);

        if(gnomes.isEmpty()) {
            System.out.println("Ошибка: в файле нет корректных данных");
            return null;
        }
        if(gnomes.size() >= count){
            System.out.println("Загружено: " + count + " гномов из файла");
        } else {
            System.out.println("В файле только " + gnomes.size() + " гномов. Загружены все.");
        }
        return gnomes;
    }
}
