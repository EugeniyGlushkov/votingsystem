package ru.alvisid.votingsystem.repository;

import ru.alvisid.votingsystem.model.Vote;

import java.util.List;

public interface VotesRepository {
    // added vote
    Vote add(Vote vote);

    // null if the vote is no in the repository
    Vote update(Vote vote);

    //false if not found
    boolean delete( int userId,int menuId);

    //null if not found
    Vote get(int userId, int menuId);

    List<Vote> getAll();

    //all votes of the user with id
    List<Vote> getAllByUserId(int userId);

    //all votes of the restaurant with id
    List<Vote> getAllByRestaurantId(int restaurantId);
}
