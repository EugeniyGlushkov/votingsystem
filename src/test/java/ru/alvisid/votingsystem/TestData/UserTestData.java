package ru.alvisid.votingsystem.TestData;

import ru.alvisid.votingsystem.model.Role;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.util.MenuUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class UserTestData {

    public static final User user_1 = getUser("Alex", Role.ROLE_USER);
    public static final User user_2 = getUser("Sindy", Role.ROLE_USER, Role.ROLE_ADMIN);
    public static final User user_3 = getUser("Fox", Role.ROLE_USER);

    public static final User new_user = new User("New_user", Role.ROLE_USER);
    public static final List <User> USERS = Arrays.asList(user_1, user_2, user_3);

    static {
        //new_user.setVotes(Collections.emptySet());
    }

    private UserTestData() {

    }

    public static User getUser(String name, Set <Role> roles) {
        return new User(TestData.getNewId(), name, roles);
    }

    public static User getUser(String name, Role role, Role... roles) {
        return new User(TestData.getNewId(), name, role, roles);
    }
}
