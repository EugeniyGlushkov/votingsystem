package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    private CrudVotesRepository crudVotesRepository;

    @Autowired
    private CrudUsersRepository crudUsersRepository;

    @Autowired
    private CrudMenusRepository crudMenusRepository;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int menuId) {
        if (!vote.isNew() && Objects.isNull(get(vote.getId()))) {
            return null;
        }

        vote.setUser(crudUsersRepository.getOne(userId));
        vote.setMenu(crudMenusRepository.getOne(menuId));
        return crudVotesRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudVotesRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return crudVotesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return crudVotesRepository.findAll(SORT_DATE_RESTAURANT_USER);
    }

    @Override
    public List<Vote> getAllByUserId(int userId) {
        return crudVotesRepository.findAllByUserId(userId, SORT_DATE_RESTAURANT);
    }

    @Override
    public List<Vote> getAllByRestaurantId(int restaurantId) {
        return crudVotesRepository.findAllByRestaurantId(restaurantId, SORT_DATE_USER);
    }
}
