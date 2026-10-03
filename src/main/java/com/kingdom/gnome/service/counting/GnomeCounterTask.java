package com.kingdom.gnome.service.counting;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.concurrent.Callable;

public class GnomeCounterTask implements Callable<Integer> {
    private final Gnome gnome;
    private final String type;
    private final String value;


    GnomeCounterTask(Gnome gnome, String type, String value) {
        this.gnome = gnome;
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
        return gnome.getName().equals(targetName) ? 1 : 0;
    }
    private int getRoleCount(String targetRole) {
        return gnome.getRole().getTitle().equals(targetRole) ? 1 : 0;
    }
    private int getEmailCount(String targetEmail) {
        return gnome.getEmail().toString().equals(targetEmail) ? 1 : 0;
    }
}
