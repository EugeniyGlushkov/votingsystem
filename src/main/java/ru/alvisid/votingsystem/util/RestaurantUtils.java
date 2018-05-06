package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Restaurant;

import java.util.Arrays;
import java.util.List;

public class RestaurantUtils {
    public static final Restaurant rest_1 = getRestaurant("Ambassador");
    public static final Restaurant rest_2 = getRestaurant("Mandalay");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(rest_1, rest_2);

    private RestaurantUtils() {
    }

    public static Restaurant getNewRestaurant(String name) {
        return new Restaurant(name);
    }

    public static Restaurant getRestaurant(String name) {
        return new Restaurant(MenuUtils.getIdCounter().incrementAndGet(), name);
    }
}
