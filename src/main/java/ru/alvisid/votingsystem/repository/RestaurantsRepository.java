package ru.alvisid.votingsystem.repository;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantsRepository {
    // null if updated restaurant is not in the repository
    Restaurant save(Restaurant restaurant);

    // false if restaurant with id is not in the repository
    boolean delete(int id);

    // null if restaurant with id is not in the repository
    Restaurant get(int id);

    // ORDERED name desc
    List<Restaurant> getAll();
}
