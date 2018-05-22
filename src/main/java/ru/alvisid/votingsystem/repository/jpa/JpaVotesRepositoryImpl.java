package ru.alvisid.votingsystem.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class JpaVotesRepositoryImpl implements VotesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return Objects.isNull(get(vote.getId())) ? null : em.merge(vote);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    //del
    /*@Override
    public void deleteAllByUserId(int userId) {
        em.createNamedQuery(Vote.DELETE_ALL_WITH_USER_ID)
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Override
    public void deleteAllByMenuId(int menuId) {
        em.createNamedQuery(Vote.DELETE_ALL_WITH_MENU_ID)
                .setParameter("menuId", menuId)
                .executeUpdate();
    }*/

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    public List <Vote> getAll() {
        return em.createNamedQuery(Vote.ALL_SORTED)
                .getResultList();
    }

    @Override
    public List <Vote> getAllByUserId(int userId) {
        return em.createNamedQuery(Vote.ALL_BY_USER_ID)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List <Vote> getAllByRestaurantId(int restaurantId) {
        return em.createNamedQuery(Vote.ALL_BY_RESTAURANT_ID)
                .setParameter("restaurantId", restaurantId)
                .getResultList();
    }
}
