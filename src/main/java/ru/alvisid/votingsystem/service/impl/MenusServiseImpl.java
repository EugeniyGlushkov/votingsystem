package ru.alvisid.votingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;
import ru.alvisid.votingsystem.service.MenusService;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static ru.alvisid.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MenusServiseImpl implements MenusService {

    private final MenusRepository repository;

    @Autowired
    public MenusServiseImpl(MenusRepository repository) {
        this.repository = repository;
    }

    @Override
    public Menu create(Menu menu) {
        return repository.save(menu);
    }

    @Override
    public void update(Menu menu) {
        checkNotFoundWithId(repository.save(menu), menu.getId());
    }

    @Override
    public Menu get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Menu> getBetween(LocalDate startDate, LocalDate endDate) {
        return repository.getBetween(startDate, endDate);
    }

    @Override
    public List <Menu> getAllByRestaurantId(int id) {
        return repository.getAllByRestaurantId(id);
    }

    @Override
    public Map<String, Float> getPriceById(int menuId) throws NotFoundException {
        return get(menuId).getMenu();
    }
}
