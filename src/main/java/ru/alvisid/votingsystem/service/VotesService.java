package ru.alvisid.votingsystem.service;

import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.List;

public interface VotesService {

    Vote create(Vote vote);

    void update(Vote vote);

    Vote get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<Vote> getAll();

    List<Vote> getAllByUserId(int userId);

    List<Vote> getAllByRestaurantId(int restaurantId);
}
