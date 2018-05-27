package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Role;
import ru.alvisid.votingsystem.model.User;

import java.util.Set;

public class UserUtils {

    private UserUtils() {
    }

    public static User getNewUser(String name, Set<Role> roles) {
        return new User(name, roles);
    }

    public static User getNewUser(String name, Role role, Role... roles) {
        return new User(name, role, roles);
    }

    public static User getZeroUser() {
        return new User(0, "zero", null);
    }
}
