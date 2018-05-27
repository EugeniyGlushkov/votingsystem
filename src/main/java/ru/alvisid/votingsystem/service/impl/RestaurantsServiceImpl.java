package ru.alvisid.votingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.repository.RestaurantsRepository;
import ru.alvisid.votingsystem.service.RestaurantsService;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.alvisid.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantsServiceImpl implements RestaurantsService {

    private RestaurantsRepository repository;

    @Autowired
    public RestaurantsServiceImpl(RestaurantsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
