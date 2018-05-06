package ru.alvisid.votingsystem.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoruVoutsRepository implements VotesRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMenusRepository.class);
    //<userId, <menuId, vote>>
    public Map<Integer, Map<Integer, Vote>> votesRepository = new ConcurrentHashMap<>();

    @Override
    public Vote save(Vote vote) {
        log.info("save {}", vote);
        return null;
    }

    @Override
    public boolean delete(int menuId, int userId) {
        log.info("delete {} - {}", menuId, userId);
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
