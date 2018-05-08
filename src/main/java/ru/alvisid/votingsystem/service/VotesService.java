package ru.alvisid.votingsystem.service;

import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.util.exception.OverTimeException;

import java.util.List;

public interface VotesService {

    Vote create(Vote vote);

    void update(Vote vote) throws OverTimeException;

    Vote get(int userId, int menuId) throws NotFoundException;

    void delete(int userId, int menuId) throws NotFoundException, OverTimeException;

    List<Vote> getAll();

    List<Vote> getAllByUserId(int userId);

    List<Vote> getAllByRestaurantId(int restaurantId);
}
