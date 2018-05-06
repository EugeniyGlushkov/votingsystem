package ru.alvisid.votingsystem.repository;

import ru.alvisid.votingsystem.model.Vote;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface VotesRepository {
    // null if updated vote is not in the repository
    Vote save(Vote vote);

    //false if not found
    boolean delete(int menuId, int userId);

    //null if not found
    Vote get(int id);

    List<Vote> getAll();

    //all votes of the user with id
    List<Vote> getAllByUserId(int userId);

    //all votes of the restaurant with id
    List<Vote> getAllByRestaurantId(int restaurantId);
}
