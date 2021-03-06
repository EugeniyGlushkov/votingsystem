package ru.alvisid.votingsystem.TestData;

import ru.alvisid.votingsystem.model.Restaurant;

import java.util.Arrays;
import java.util.List;

public class RestaurantTestData {
    public static final Restaurant rest_1 = getRestaurant("Ambassador");
    public static final Restaurant rest_2 = getRestaurant("Eleon");
    public static final Restaurant rest_3 = getRestaurant("Mandalay");

    public static final Restaurant rest_new = getNewRestaurant("Kazbek_NEW");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(rest_1, rest_2);

    private RestaurantTestData() {
    }

    public static Restaurant getRestaurant(String name) {
        return new Restaurant(TestData.getNewId(), name);
    }

    public static Restaurant getNewRestaurant(String name) {
        return new Restaurant(name);
    }
}
