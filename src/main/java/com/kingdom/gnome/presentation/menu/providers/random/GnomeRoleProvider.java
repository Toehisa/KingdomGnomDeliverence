package com.kingdom.gnome.presentation.menu.providers.random;

import com.kingdom.gnome.dao.entity.GnomeRole;

import java.util.Random;

public enum GnomeRoleProvider {
    ROLES;

    private static final GnomeRole[] roles = GnomeRole.values();
    private static final int rolesSize = roles.length;

    public GnomeRole provideRoleBySeed(Random random) {
        return roles[random.nextInt(rolesSize)];
    }
}
