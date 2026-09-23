package com.kingdom.gnome.dao.perform;

import com.kingdom.gnome.dao.entity.Email;
import com.kingdom.gnome.dao.entity.Gnome;
import com.kingdom.gnome.dao.entity.GnomeRole;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class GnomeFileReader {
    public List<Gnome> readGnomesFromFile(File file, int count){
        var stat = new Object() {
            final AtomicInteger lineNumber = new AtomicInteger(0);
            final AtomicInteger successfulCount = new AtomicInteger(0);
            final AtomicInteger errorCount = new AtomicInteger(0);
        };

        List<Gnome> gnomes = new ArrayList<>();

        System.out.println("\n---- Начинаем чтение файла ----");
        System.out.println("-------------------------------");

        try(Stream<String> lines = new BufferedReader(new FileReader(file)).lines()) {
            lines.anyMatch(line -> {
                stat.lineNumber.getAndIncrement();
                if(line.isBlank() || line.contains("#")){
                    return false;
                }
                try {
                    String[] parts = line.split(";");
                    if(parts.length != 3) {
                        throw new IllegalStateException("Неверный формат");
                    }
                    Gnome gnome = new Gnome.GnomeBuilder()
                            .name(parts[0])
                            .role(GnomeRole.fromTitle(parts[1]))
                            .email(new Email(parts[2]))
                            .build();
                    gnomes.add(gnome);
                    stat.successfulCount.getAndIncrement();
                    if(stat.successfulCount.intValue() == count) {
                        return true;
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка в строке " + stat.lineNumber + ": " + e.getMessage());
                    System.out.println("Содержимое строки: " + line);
                    stat.errorCount.getAndIncrement();
                }
                return false;
            });
        } catch (IOException e) {
            System.err.println("Файл не найден: " + e);
        }

        System.out.println("\n--- Статистика чтения файла ---");
        System.out.println("Успешно распознано: " + stat.successfulCount + " гномов");
        System.out.println("Ошибок при чтении: " + stat.errorCount + " строк");
        System.out.println("Всего обработано строк: " + stat.lineNumber);
        System.out.println("-------------------------------");
        return gnomes;
    }

    public File findFile(String filename) {
        File file = new File(filename.replaceFirst("~", System.getProperty("user.home")));

        if (file.exists() && file.isFile()) {
            System.out.println("Файл найден по указанному пути: " + file.getAbsolutePath());
            return file;
        }

        String[] possiblePaths = {
                "src/main/resources/" + filename,
                "resources/" + filename,
                "src/resources/" + filename,
                filename
        };

        System.out.println("Поиск файла в проекте...");

        for (String path : possiblePaths) {
            file = new File(path);
            if (file.exists() && file.isFile()) {
                System.out.println("Файл найден: " + file.getAbsolutePath());
                return file;
            }
        }

        System.out.println("Файл не найден в проекте: " + filename);
        return null;
    }
}
