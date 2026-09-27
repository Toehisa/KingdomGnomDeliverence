package com.kingdom.gnome.presentation.menu.routes;

public enum MenuRoutes {
    MAIN,
    RANDOM,
    FILE,
    MANUAL,
    EXIT;

    public static MenuRoutes fromStr(String str, MenuRoutes...routes) {
        try {
            int routeIdx = Integer.parseInt(str) - 1;
            if (isValidRoute(routes, routeIdx)) {return routes[routeIdx];}
        } catch (NumberFormatException ignore){}

        throw new IllegalArgumentException("Пункт меню не найден, попробуйте еще раз");
    }

    private static boolean isValidRoute(MenuRoutes[] routes, int routeIdx) {
        return routeIdx > -1 && routeIdx < routes.length;
    }
}
