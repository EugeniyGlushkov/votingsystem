package ru.alvisid.votingsystem.service;

import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.List;

public interface UsersService {

    User create(User user);

    void update(User user);

    User get(int id) throws NotFoundException;

    void delete(int id) throws NotFoundException;

    List<User> getAll();
}
