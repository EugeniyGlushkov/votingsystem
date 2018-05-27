package ru.alvisid.votingsystem.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository

@Transactional(readOnly = true)
public class JpaMenusRepositoryImpl implements MenusRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (menu.isNew()) {
            em.persist(menu);
            return menu;
        } else {
            return em.merge(menu);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Menu.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public List<Menu> getAll() {
        return em.createNamedQuery(Menu.ALL_SORTED, Menu.class)
                .getResultList();
    }

    @Override
    public List <Menu> getBetween(LocalDate startDate, LocalDate endDate) {
        return em.createNamedQuery(Menu.ALL_BEETWEN, Menu.class)
                .setParameter(1, startDate)
                .setParameter(2, endDate)
                .getResultList();
    }
}
