package ru.alvisid.votingsystem.service;

import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.util.exception.OverTimeException;

import java.util.List;

public interface VotesService {

    Vote create(Vote vote, int userId, int menuId);

    void update(Vote vote, int userId, int menuId) throws OverTimeException;

    Vote get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException, OverTimeException;

    List<Vote> getAll();

    List<Vote> getAllByUserId(int userId);

    List<Vote> getAllByRestaurantId(int restaurantId);
}
