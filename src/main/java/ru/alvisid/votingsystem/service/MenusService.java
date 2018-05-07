package ru.alvisid.votingsystem.service;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MenusService {

    Menu create(Menu menu);

    void update(Menu menu);

    Menu get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Menu> getAll();

    List<Menu> getBetween(LocalDate startDate, LocalDate endDate);

    Map<String, Float> priceById(int menuId) throws NotFoundException;
}
