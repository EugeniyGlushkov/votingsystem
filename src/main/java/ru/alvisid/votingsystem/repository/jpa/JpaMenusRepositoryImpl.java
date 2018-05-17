package ru.alvisid.votingsystem.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JpaMenusRepositoryImpl implements MenusRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Menu save(Menu menu) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Menu get(int id) {
        return em.find(Menu.class, id);
    }

    @Override
    public List<Menu> getAll() {
        return null;
    }

    @Override
    public List <Menu> getBetween(LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
