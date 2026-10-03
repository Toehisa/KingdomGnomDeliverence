package com.kingdom.gnome.service.counting;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

public class GnomeCounterService {
    private final ForkJoinPool pool;

    public GnomeCounterService(ForkJoinPool pool) {
        this.pool = pool;
    }

    public int getOccurrencesCount(String type, String value, List<Gnome> gnomes) {
        try {
            return Math.toIntExact(pool.submit(() -> gnomes.parallelStream().filter(filterByOccurrence(type, value)).count()).get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.printf("Подсчет прерван: %s", e);
        } catch (ExecutionException e) {
            System.err.printf("Ошибка в стриме: %s", e);
        } catch (ArithmeticException e) {
            System.out.printf("Невероятно много вхождений, ошибка в расчете: %s", e);
        }
        return 0;
    }

    private Predicate<Gnome> filterByOccurrence(String type, String value) {
        return switch (type) {
            case "NAME" -> gnome -> gnome.getName().equals(value);
            case "ROLE" -> gnome -> gnome.getRole().getTitle().equals(value);
            case "EMAIL" -> gnome -> gnome.getEmail().toString().equals(value);
            default -> throw new IllegalArgumentException("Неверное поле для фильтрации: " + type);
        };
    }
}

