package ru.alvisid.votingsystem.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.TestData.MenuTestData;
import ru.alvisid.votingsystem.TestData.UserTestData;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.repository.UsersRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryUsersRepository implements UsersRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUsersRepository.class);
    private Map<Integer, User> userRepository = new ConcurrentHashMap<>();

    {
        UserTestData.USERS.forEach(user -> userRepository.put(user.getId(), user));
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);

        if (user.isNew()){
            user.setId(MenuTestData.getIdCounter().incrementAndGet());
            userRepository.put(user.getId(), user);
            return user;
        }

        return userRepository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return userRepository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return userRepository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return userRepository.values().stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }
}
