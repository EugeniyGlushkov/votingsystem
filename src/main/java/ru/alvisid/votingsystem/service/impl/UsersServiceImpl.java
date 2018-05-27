package ru.alvisid.votingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.repository.UsersRepository;
import ru.alvisid.votingsystem.service.UsersService;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.alvisid.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository repository;

    @Autowired
    public UsersServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
