package ru.alvisid.votingsystem.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.repository.RestaurantsRepository;
import ru.alvisid.votingsystem.util.MenuUtils;
import ru.alvisid.votingsystem.util.RestaurantUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryRestaurantsRepository implements RestaurantsRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryRestaurantsRepository.class);
    private Map<Integer, Restaurant> restaurantsRepository = new ConcurrentHashMap<>();

    {
        RestaurantUtils.RESTAURANTS.forEach(restaurant -> restaurantsRepository.put(restaurant.getId(), restaurant));
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        log.info("save {}", restaurant);

        if (restaurant.isNew()) {
            restaurant.setId(MenuUtils.getIdCounter().incrementAndGet());
            restaurantsRepository.put(restaurant.getId(), restaurant);
            return restaurant;
        }

        return restaurantsRepository.computeIfPresent(restaurant.getId(), (id, oldRestaurant) -> restaurant);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return restaurantsRepository.remove(id) != null;
    }

    @Override
    public Restaurant get(int id) {
        log.info("get {}", id);
        return restaurantsRepository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        log.info("getAll");
        return restaurantsRepository.values().stream()
                .sorted(Comparator.comparing(Restaurant::getName))
                .collect(Collectors.toList());
    }
}
