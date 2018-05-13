package ru.alvisid.votingsystem.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.repository.RestaurantsRepository;

import java.util.List;

@Repository
public class JdbcRestaurantsRepositoryImpl implements RestaurantsRepository {
    @Override
    public Restaurant save(Restaurant restaurant) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }
}
