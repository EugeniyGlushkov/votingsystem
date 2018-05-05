package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Restaurant;

public class RestaurantUtils {
    public static final Restaurant rest_1 = getNewRestaurant("Ambassador");
    public static final Restaurant rest_2 = getNewRestaurant("Mandalay");

    private RestaurantUtils() {
    }

    public static Restaurant getNewRestaurant(String name) {
        return new Restaurant(MenuUtils.getIdCounter().incrementAndGet(), name);
    }
}
