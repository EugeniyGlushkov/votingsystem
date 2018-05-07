package ru.alvisid.votingsystem.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoruVoutsRepository implements VotesRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMenusRepository.class);
    //<userId, <menuId, vote>>
    public Map <Integer, Map <Integer, Vote>> votesRepository = new ConcurrentHashMap <>();

    @Override
    public Vote save(Vote vote) {
        log.info("save {}", vote);
        int userId = vote.getUser().getId();
        int menuId = vote.getMenu().getId();

        Map <Integer, Vote> votesByUsId = votesRepository.get(userId);

        if (votesByUsId == null) {
            Map <Integer, Vote> menuIdVotes = new ConcurrentHashMap <>();
            menuIdVotes.put(menuId, vote);
            votesRepository.put(userId, menuIdVotes);
        } else {
            votesByUsId.put(menuId, vote);
        }

        return vote;
    }

    @Override
    public boolean delete(int userId, int menuId) {
        log.info("delete userId={} - menuId={}", menuId, userId);
        Map <Integer, Vote> votesByUsId = votesRepository.get(userId);

        return votesByUsId == null ? false : votesByUsId.remove(menuId) != null;
    }

    @Override
    public Vote get(int userId, int menuId) {
        log.info("get userId={} - menuId={}", userId, menuId);
        Map <Integer, Vote> votesByUsId = votesRepository.get(userId);
        return votesByUsId == null ? null : votesByUsId.get(menuId);
    }

    @Override
    public List <Vote> getAll() {
        log.info("getAll");
        List <Vote> allVotes = new ArrayList <>();
        votesRepository.values().forEach(integerVoteMap -> integerVoteMap.values().forEach(vote -> allVotes.add(vote)));
        return allVotes;
    }

    @Override
    public List <Vote> getAllByUserId(int userId) {
        log.info("getAllByUserId {}", userId);
        return new ArrayList <>(votesRepository.get(userId).values());
    }

    @Override
    public List <Vote> getAllByRestaurantId(int restaurantId) {
        log.info("getAllByRestaurantId {}", restaurantId);
        List <Vote> votesRestId = new ArrayList <>();

        votesRepository.values().forEach(integerVoteMap -> integerVoteMap.values().forEach(vote -> {
            if (vote.getMenu().getRestaurant().getId() == restaurantId) {
                votesRestId.add(vote);
            }
        }));

        return votesRestId;
    }
}
