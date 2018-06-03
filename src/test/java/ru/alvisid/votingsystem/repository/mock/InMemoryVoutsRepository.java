package ru.alvisid.votingsystem.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryVoutsRepository implements VotesRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMenusRepository.class);
    //<userId, <menuId, vote>>
    public Map <Integer, Map <Integer, Vote>> votesRepository = new ConcurrentHashMap <>();

    @Override
    public Vote save(Vote vote, int userId, int restaurantId) {
        log.info("add {}", vote);
        int usId = vote.getUser().getId();
        int menuId = vote.getMenu().getId();

        Map <Integer, Vote> votesByUsId = votesRepository.get(usId);

        if (votesByUsId == null) {
            Map <Integer, Vote> menuIdVotes = new ConcurrentHashMap <>();
            menuIdVotes.put(menuId, vote);
            votesRepository.put(usId, menuIdVotes);
            return vote;
        } else {
            votesByUsId.put(menuId, vote);
        }

        return vote;
    }

    /*@Override
    public Vote update(Vote vote) {
        log.info("update {}", vote);
        int userId = vote.getUser().getId();
        int menuId = vote.getMenu().getId();

        Map <Integer, Vote> votesByUsId = votesRepository.get(userId);

        if (Objects.isNull(votesByUsId)) {
            return null;
        }

        return votesByUsId.computeIfPresent(menuId, (id, oldVote) -> vote);
    }*/

    @Override
    public boolean delete(int id) {
        /*log.info("delete userId={} - menuId={}", menuId, userId);
        Map <Integer, Vote> votesByUsId = votesRepository.get(userId);

        return votesByUsId == null ? false : votesByUsId.remove(menuId) != null;*/
        return false;
    }

    @Override
    public Vote get(int id) {
        /*log.info("get userId={} - menuId={}", userId, menuId);
        Map <Integer, Vote> votesByUsId = votesRepository.get(userId);
        return votesByUsId == null ? null : votesByUsId.get(menuId);*/
        return null;
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

    //del
    /*@Override
    public void deleteAllByUserId(int userId) {

    }

    @Override
    public void deleteAllByMenuId(int menuId) {

    }*/
}
