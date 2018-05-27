package ru.alvisid.votingsystem.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.repository.UsersRepository;

import java.util.List;

@Repository
public class JdbcUserRepositoryImpl implements UsersRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
