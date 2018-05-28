package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alvisid.votingsystem.model.Vote;

import java.util.List;
import java.util.Optional;

public interface CrudVotesRepository extends JpaRepository<Vote,Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<Vote> findById(Integer integer);

    @Override
    List<Vote> findAll(Sort sort);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    List<Vote> findAllByUserId(@Param("userId") int userId, Sort sort);

    @Query("SELECT v FROM Vote v WHERE v.menu.restaurant.id=:restaurantId")
    List<Vote> findAllByRestaurantId(@Param("restaurantId") int restaurantId, Sort sort);
}
