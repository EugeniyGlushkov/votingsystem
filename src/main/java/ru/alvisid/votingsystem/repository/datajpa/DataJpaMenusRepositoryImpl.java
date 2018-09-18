package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Repository
public class DataJpaMenusRepositoryImpl implements MenusRepository {
    private static final Sort SORT_DATE_NAME = new Sort(Sort.Direction.ASC, "date", "restaurant.name");
    private static final Sort SORT_DATE = new Sort(Sort.Direction.ASC, "date");

    @Autowired
    CrudMenusRepository crudRepository;

    @Override
    @Transactional
    public Menu save(Menu menu) {
        if (!menu.isNew() && Objects.isNull(get(menu.getId()))) {
            return null;
        }

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

    @Override
    public List <Menu> getAllByRestaurantId(int restaurantId) {
        return crudRepository.findAllByRestaurantId(restaurantId, SORT_DATE);
    }
}
