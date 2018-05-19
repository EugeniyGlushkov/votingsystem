package ru.alvisid.votingsystem.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;

import java.util.List;

@Repository
public class JdbcVotesRepositoryImpl implements VotesRepository {

    @Override
    public Vote save(Vote vote) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Vote get(int id) {
        return null;
    }

    @Override
    public List<Vote> getAll() {
        return null;
    }

    @Override
    public List<Vote> getAllByUserId(int userId) {
        return null;
    }

    @Override
    public List<Vote> getAllByRestaurantId(int restaurantId) {
        return null;
    }
}
