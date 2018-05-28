package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.repository.UsersRepository;

import java.util.List;
import java.util.Objects;

@Repository
public class DataJpaUsersRepositoryImpl implements UsersRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    CrudUsersRepository crudRepository;

    @Override
    public User save(User user) {
        if (!user.isNew() && Objects.isNull(get(user.getId()))) {
            return null;
        }

        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME);
    }
}
