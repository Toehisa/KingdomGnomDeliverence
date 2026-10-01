package com.kingdom.gnome.service.counting;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.List;
import java.util.concurrent.Callable;

public class GnomeCounterTask implements Callable<Integer> {
    private final List<Gnome> gnomes;
    private final String type;
    private final String value;
    private final int rightBound;
    private final int leftBound;

    GnomeCounterTask(List<Gnome> gnomes, String type, String value, int leftBound, int rightBound) {
        this.gnomes = gnomes;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.type = type;
        this.value = value;
    }

    @Override
    public Integer call() throws IllegalArgumentException {
        return getCountByTarget();
    }

    private int getCountByTarget() {
        return switch (type) {
            case "NAME" -> getNameCount(value);
            case "ROLE" -> getRoleCount(value);
            case "EMAIL" -> getEmailCount(value);
            default -> throw new IllegalArgumentException("Неверное поле");
        };
    }

    private int getNameCount(String targetName) {
        int result = 0;

        for (int i = leftBound; i <= rightBound; i++) {
            String gnomeName = gnomes.get(i).getName();

            if (gnomeName.equals(targetName)) {
                result++;
            }
        }

        return result;
    }
    private int getRoleCount(String targetRole) {
        int result = 0;

        for (int i = leftBound; i <= rightBound; i++) {
            String gnomeRole = gnomes.get(i).getRole().getTitle();

            if (gnomeRole.equals(targetRole)) {
                result++;
            }
        }

        return result;
    }

    private int getEmailCount(String targetEmail) {
        int result = 0;

        for (int i = leftBound; i <= rightBound; i++) {
            String gnomeEmail = gnomes.get(i).getEmail().toString();

            if (gnomeEmail.equals(targetEmail)) {
                result++;
            }
        }

        return result;
    }
}
