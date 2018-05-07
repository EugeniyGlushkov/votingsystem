package ru.alvisid.votingsystem.service;

import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.List;

public interface RestauranrsService {

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    Restaurant get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Restaurant> getAll();
}
