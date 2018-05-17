package ru.alvisid.votingsystem.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;
import ru.alvisid.votingsystem.util.VoteUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaVotesRepositoryImpl implements VotesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Vote add(Vote vote) {
        return null;
    }

    @Override
    public Vote update(Vote vote) {
        return null;
    }

    @Override
    public boolean delete(int userId, int menuId) {
        return false;
    }

    @Override
    public Vote get(int userId, int menuId) {
        return em.find(Vote.class, new Vote.VoteCompositeKey(userId, menuId));
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    @Override
    public List <Vote> getAllByUserId(int userId) {
        return null;
    }

    @Override
    public List <Vote> getAllByRestaurantId(int restaurantId) {
        return null;
    }
}
