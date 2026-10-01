package com.kingdom.gnome.presentation.menu.routes;

public enum SearchRoutes {
    NAME,
    ROLE,
    EMAIL;

    public static SearchRoutes fromInt(int num) {
        SearchRoutes[] routes = SearchRoutes.values();

        int routeIdx = num - 1;

        if (isValidRoute(routes, routeIdx)) {
            return routes[routeIdx];
        }

        throw new IllegalArgumentException("Пункт меню не найден, попробуйте еще раз");
    }


    private static boolean isValidRoute(SearchRoutes[] routes, int routeIdx) {
        return routeIdx >= 0 && routeIdx < routes.length;
    }
}
