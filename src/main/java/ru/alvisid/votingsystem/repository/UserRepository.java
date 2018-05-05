package ru.alvisid.votingsystem.repository;

import ru.alvisid.votingsystem.model.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    //false if not found
    boolean delete(int id);

    //null if not found
    User get(int id);

    // ORDERED name asc
    List<User> getAll();
}
