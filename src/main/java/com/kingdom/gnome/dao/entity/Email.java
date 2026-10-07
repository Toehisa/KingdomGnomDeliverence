package com.kingdom.gnome.dao.entity;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {
    private static final Pattern EMAIL_VALIDATOR =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$");

    public Email {
        Objects.requireNonNull(value, "Email == null");

        if (!EMAIL_VALIDATOR.matcher(value).matches()) {
            throw new IllegalArgumentException("Некорректный формат email: " + value);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
