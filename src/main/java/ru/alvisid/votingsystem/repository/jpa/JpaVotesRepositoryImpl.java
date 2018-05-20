package ru.alvisid.votingsystem.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;
import ru.alvisid.votingsystem.util.MenuUtils;
import ru.alvisid.votingsystem.util.UserUtils;
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
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    public boolean delete(int id) {
        em.createNamedQuery(Vote.USER_ZERO_DELETE)
                .setParameter("zeroId", UserUtils.getZeroUser())
                .setParameter("id", id)
                .executeUpdate();
        em.createNamedQuery(Vote.MENU_ZERO_DELETE)
                .setParameter("zeroId", MenuUtils.getZeroMenu())
                .setParameter("id", id)
                .executeUpdate();
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
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
