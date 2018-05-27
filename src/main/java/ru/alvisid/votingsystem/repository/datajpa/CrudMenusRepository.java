package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CrudMenusRepository extends JpaRepository<Menu,Integer> {
    @Override
    @Transactional
    Menu save(Menu menu);

    @Transactional
    @Modifying
    @Query("DELETE FROM Menus m WHERE m.id=:id")
    int delete (@Param("id") int id);

    @Override
    Optional<Menu> findById(Integer id);

    @Override
    List<Menu> findAll(Sort sort);

    @Query("SELECT m FROM Menu m WHERE m.date>=:startDate AND m.date<=:endDate ORDER BY m.date, m.restaurant.name")
    List<Menu> findAllBetween(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
}
