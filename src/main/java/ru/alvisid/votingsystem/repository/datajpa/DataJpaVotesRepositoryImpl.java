package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;

import java.util.List;
import java.util.Objects;

@Repository
public class DataJpaVotesRepositoryImpl implements VotesRepository {
    private static final Sort SORT_DATE_RESTAURANT_USER = new Sort(Sort.Direction.ASC, "menu.date", "menu.restaurant.name", "user.name");
    private static final Sort SORT_DATE_RESTAURANT = new Sort(Sort.Direction.ASC, "menu.date", "menu.restaurant.name");
    private static final Sort SORT_DATE_USER = new Sort(Sort.Direction.ASC, "menu.date", "user.name");

    @Autowired
    CrudVotesRepository crudRepository;

    @Override
    public Vote save(Vote vote) {
        if (!vote.isNew() && Objects.isNull(get(vote.getId()))) {
            return null;
        }

        return crudRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return crudRepository.findAll(SORT_DATE_RESTAURANT_USER);
    }

    @Override
    public List<Vote> getAllByUserId(int userId) {
        return crudRepository.findAllByUserId(userId, SORT_DATE_RESTAURANT);
    }

    @Override
    public List<Vote> getAllByRestaurantId(int restaurantId) {
        return crudRepository.findAllByRestaurantId(restaurantId, SORT_DATE_USER);
    }
}
