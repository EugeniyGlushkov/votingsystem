package ru.alvisid.votingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.VotesRepository;
import ru.alvisid.votingsystem.service.VotesService;
import ru.alvisid.votingsystem.util.DateTimeUtil;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.util.exception.OverTimeException;

import java.util.List;

import static ru.alvisid.votingsystem.util.ValidationUtil.checkNotFound;
import static ru.alvisid.votingsystem.util.ValidationUtil.checkOverTimeVout;

@Service
public class VotesServiceImpl implements VotesService {

    private VotesRepository repository;

    @Autowired
    public VotesServiceImpl(VotesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote create(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public void update(Vote vote) throws OverTimeException {
        checkOverTimeVout(DateTimeUtil.OVER_TIME, "You can not change your vout.");
        checkNotFound(repository.save(vote), "userId=" + vote.getUser().getId() + " and menuId=" + vote.getMenu().getId());
    }

    @Override
    public Vote get(int id) throws NotFoundException {
        return checkNotFound(repository.get(id), "id=" + id);
    }

    @Override
    public void delete(int userId, int menuId) throws NotFoundException, OverTimeException {
        checkOverTimeVout(DateTimeUtil.OVER_TIME, "You can not delete your vout.");
        checkNotFound(repository.delete(userId, menuId), "userId=" + userId + " and menuId=" + menuId);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List <Vote> getAllByUserId(int userId) {
        return repository.getAllByUserId(userId);
    }

    @Override
    public List <Vote> getAllByRestaurantId(int restaurantId) {
        return repository.getAllByRestaurantId(restaurantId);
    }
}
