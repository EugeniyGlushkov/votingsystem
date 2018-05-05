package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Role;
import ru.alvisid.votingsystem.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class UserUtils {
    public static final User user_1 = getUser("Alex", Role.ROLE_USER);
    public static final User user_2 = getUser("Sindy", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User user_3 = getUser("Fox", Role.ROLE_USER);
    public static final List<User> USERS = Arrays.asList(user_1, user_2, user_3);

    private UserUtils() {
    }

    public static User getUser(String name, Set<Role> roles) {
        return new User(MenuUtils.getIdCounter().incrementAndGet(), name, roles);
    }

    public static User getUser(String name, Role role, Role... roles) {
        return new User(MenuUtils.getIdCounter().incrementAndGet(), name, role, roles);
    }

    public static User getNewUser(String name, Set<Role> roles) {
        return new User(name, roles);
    }

    public static User getNewUser(String name, Role role, Role... roles) {
        return new User(name, role, roles);
    }
}
