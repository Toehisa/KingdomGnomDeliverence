package com.kingdom.gnome.service.counting;

import com.kingdom.gnome.dao.entity.Gnome;

import java.util.List;

public class GnomeCounterTask implements Runnable{
    private final List<Gnome> gnomes;
    private final String type;
    private final String value;
    private final int rightBound;
    private final int leftBound;
    private int result = 0;

    GnomeCounterTask(List<Gnome> gnomes, String type, String value, int leftBound, int rightBound) {
        this.gnomes = gnomes;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.type = type;
        this.value = value;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        setCountByTarget();
    }

    private void setCountByTarget() {
        switch (type) {
            case "NAME" -> setNameCount(value);
            case "ROLE" -> setRoleCount(value);
            case "EMAIL" -> setEmailCount(value);
            default -> throw new IllegalArgumentException("Неверное поле");
        };
    }

    private void setNameCount(String targetName) {
        for (int i = leftBound; i <= rightBound; i++) {
            String gnomeName = gnomes.get(i).getName();

            if (gnomeName.equals(targetName)) {
                result++;
            }
        }
    }
    private void setRoleCount(String targetRole) {
        for (int i = leftBound; i <= rightBound; i++) {
            String gnomeRole = gnomes.get(i).getRole().getTitle();

            if (gnomeRole.equals(targetRole)) {
                result++;
            }
        }
    }

    private void setEmailCount(String targetEmail) {
        for (int i = leftBound; i <= rightBound; i++) {
            String gnomeEmail = gnomes.get(i).getEmail().toString();

            if (gnomeEmail.equals(targetEmail)) {
                result++;
            }
        }
    }
}
