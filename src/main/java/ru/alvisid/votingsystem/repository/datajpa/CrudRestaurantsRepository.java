package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.votingsystem.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface CrudRestaurantsRepository extends JpaRepository<Restaurant,Integer> {
    @Override
    @Transactional
    Restaurant save(Restaurant s);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    //int deleteById(int id);
    int delete(@Param("id") int id);

    @Override
    Optional<Restaurant> findById(Integer integer);

    @Override
    List<Restaurant> findAll(Sort sort);
}
