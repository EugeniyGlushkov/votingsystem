package ru.alvisid.votingsystem.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.votingsystem.TestData.TestData;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collections;

import static ru.alvisid.votingsystem.TestData.TestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@RunWith(SpringRunner.class)
public class UsersServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private UsersService service;

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
}
