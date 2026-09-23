package com.kingdom.gnome.dao.entity;

public final class Gnome {
    private final String name;
    private final GnomeRole role;
    private final Email email;

    private Gnome(String name, GnomeRole role, Email email) {
        this.name = name;
        this.role = role;
        this.email = email;
    }

    public String getName() { return name; }
    public GnomeRole getRole() { return role; }
    public Email getEmail() { return email; }

    @Override
    public String toString() {
        return "Gnome{" +
                "name='" + name + '\'' +
                ", role=" + role +
                ", email=" + email +
                '}';
    }

    public static GnomeBuilder builder() { return new GnomeBuilder(); }

    public static class GnomeBuilder {
        private String name;
        private GnomeRole role;
        private Email email;

        public GnomeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public GnomeBuilder role(GnomeRole role) {
            this.role = role;
            return this;
        }

        public GnomeBuilder email(Email email) {
            this.email = email;
            return this;
        }

        public Gnome build() {
            if (name == null || role == null) {throw  new IllegalStateException("Дай имя гному и роль");}
            return new Gnome(name, role, email);
        }
    }
}
