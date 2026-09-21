package com.kingdom.gnome.dao.entity;

public record Email(String value) {
    public Email {
        if (value == null || !value.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Некорректный email: " + value);
        }
    }
}
