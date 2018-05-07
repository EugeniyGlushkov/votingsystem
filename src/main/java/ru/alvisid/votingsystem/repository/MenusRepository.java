package ru.alvisid.votingsystem.repository;

import ru.alvisid.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenusRepository {
    // null if updated menu is not in the repository
    Menu save(Menu menu);

    // false if menu with id is not in the repository
    boolean delete(int id);

    // null if menu with id is not in the repository
    Menu get(int id);

    // ORDERED dateTime desc
    List<Menu> getAll();

    // ORDERED dateTime desc
    List<Menu> getBetween(LocalDate startDate, LocalDate endDate);
}
