package ru.alvisid.votingsystem.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;
import ru.alvisid.votingsystem.util.VoteUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class JpaVotesRepositoryImpl implements VotesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Vote save(Vote vote) {
        if (Objects.isNull(get(vote.getUserId(), vote.getMenuId()))) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
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
