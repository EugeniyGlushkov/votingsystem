package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.repository.RestaurantsRepository;

import java.util.List;
import java.util.Objects;

@Repository
public class DataJpaRestaurantsRepositoryImpl implements RestaurantsRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudRestaurantsRepository crudRepository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && Objects.isNull(get(restaurant.getId()))) {
            return null;
        }

        return crudRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }
}
