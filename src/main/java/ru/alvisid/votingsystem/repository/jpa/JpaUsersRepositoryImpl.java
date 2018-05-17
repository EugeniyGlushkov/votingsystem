package ru.alvisid.votingsystem.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.repository.UsersRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaUsersRepositoryImpl implements UsersRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
