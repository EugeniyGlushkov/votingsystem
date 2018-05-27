package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;

import java.time.LocalDate;
import java.util.List;

public class DataJpaMenusRepositoryImpl implements MenusRepository {
    private static final Sort SORT_DATE_NAME = new Sort(Sort.Direction.ASC, "date", "restaurant.name");

    @Autowired
    CrudMenusRepository crudRepository;

    @Override
    public Menu save(Menu menu) {
        return crudRepository.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Menu> getAll() {
        return crudRepository.findAll(SORT_DATE_NAME);
    }

    @Override
    public List<Menu> getBetween(LocalDate startDate, LocalDate endDate) {
        return crudRepository.findAllBetween(startDate, endDate);
    }
}
