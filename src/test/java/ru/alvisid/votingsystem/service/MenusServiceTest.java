package ru.alvisid.votingsystem.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static ru.alvisid.votingsystem.TestData.MenuTestData.assertMatch;
import static ru.alvisid.votingsystem.TestData.MenuTestData.*;
import static ru.alvisid.votingsystem.TestData.TestData.*;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.List;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenusServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private MenusService service;

    @Test
    public void create() {
        Menu expectedMenu = new Menu(NEW_MENU);
        Menu createdMenu = service.create(NEW_MENU);
        expectedMenu.setId(createdMenu.getId());
        assertMatch(createdMenu, expectedMenu);
    }

    @Test
    public void get() {
        Menu expectedMenu = MENU_1;
        Menu gotMenu = service.get(expectedMenu.getId());
        assertMatchIgnoringFields(gotMenu, expectedMenu, "restaurant", "price", "votes");

    }

    @Test
    public void delete() {
        service.delete(MENU_1.getId());
        assertMatchIgnoringFields(service.getAll(), MENU_2, MENU_3, MENU_4);
    }

    @Test()
    public void deleteNotFoundExc() {
        expectedException.expect(NotFoundException.class);
        service.delete(RESTAURANT_1.getId());
    }

    @Test
    public void getAll() {
        List<Menu> all = service.getAll();
        assertMatchIgnoringFields(all, MENU_1, MENU_2, MENU_3, MENU_4);
    }
}
