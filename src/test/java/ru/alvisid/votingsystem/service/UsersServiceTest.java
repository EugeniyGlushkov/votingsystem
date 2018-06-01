package ru.alvisid.votingsystem.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.votingsystem.TestData.TestData;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.Role;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.alvisid.votingsystem.TestData.TestData.*;

public class UsersServiceTest extends AbstractServiceTest {

    @Autowired
    private UsersService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
    }

    @Test
    public void create() {
        User createUser = new User(NEW_USER);
        service.create(createUser);
        createUser.setVotes(Collections.emptySet());
        assertMatch(service.get(createUser.getId()), createUser/*, "votes"*/);
    }

    @Test
    public void update() {
        User updatedUser = new User(USER_1);
        updatedUser.setName("Updated_user");
        service.update(updatedUser);
        assertMatch(service.get(updatedUser.getId()), updatedUser/*, "votes"*/);
    }

    @Test
    public void updateNotFound() {
        expectedException.expect(NotFoundException.class);
        User updatedUser = new User(USER_1);
        updatedUser.setId(MENU_2.getId());
        service.update(updatedUser);
    }

    @Test
    public void get() {
        User expectedUser = USER_1;
        User actualUser = service.get(expectedUser.getId());
        assertMatch(actualUser, expectedUser/*, "votes"*/);
    }

    @Test
    public void getNotFound() {
        expectedException.expect(NotFoundException.class);
        service.get(MENU_1.getId());
    }

    @Test
    public void delete() {
        service.delete(USER_1.getId());
        System.out.println(service.getAll());
        assertMatch(service.getAll(), Arrays.asList(USER_3, USER_2)/*, "votes"*/);
    }

    @Test
    public void deleteNotFound() {
        expectedException.expect(NotFoundException.class);
        service.delete(MENU_2.getId());
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(), Arrays.asList(USER_1, USER_3, USER_2)/*, "votes"*/);
    }

    @Test
    public void testValidation() {
        validateRootCause(() -> service.create(new User(null, Role.ROLE_USER))
                , ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User("qqqqqqqqqqwwwwwwwwww" +
                        "qqqqqqqqqqwwwwwwwwww" +
                        "qqqqqqqqqqwwwwwwwwww" +
                        "qqqqqqqqqqwwwwwwwwww" +
                        "qqqqqqqqqqwwwwwwwwww" +
                        "a", Role.ROLE_USER))
                , ConstraintViolationException.class);
        validateRootCause(() -> service.create(new User(null, "Marco", new HashSet <Role>(), null))
                , ConstraintViolationException.class);
    }
}
