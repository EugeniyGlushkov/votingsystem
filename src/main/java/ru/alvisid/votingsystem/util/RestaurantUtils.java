package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Restaurant;

public class RestaurantUtils {

    private RestaurantUtils() {
    }

    public static Restaurant getNewRestaurant(String name) {
        return new Restaurant(name);
    }

}
