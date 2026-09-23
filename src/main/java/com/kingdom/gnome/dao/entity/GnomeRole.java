package com.kingdom.gnome.dao.entity;

public enum GnomeRole {
    MINER("Шахтёр", 20),
    BLACKSMITH("Кузнец", 25),
    WARRIOR("Воин", 30),
    BUILDER("Строитель", 15),
    JEWELER("Ювелир", 10),
    KING("Король", 100);

    private final String title;
    private final int baseStamina;

    GnomeRole(String title, int baseStamina) {
        this.title = title;
        this.baseStamina = baseStamina;
    }

    public String getTitle() {
        return title;
    }

    public int getBaseStamina() {
        return baseStamina;
    }

    public static GnomeRole fromTitle(String title) {
        for (GnomeRole role : values()) {
            if (role.title.equalsIgnoreCase(title)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Неизвестная роль: " + title);
    }
}