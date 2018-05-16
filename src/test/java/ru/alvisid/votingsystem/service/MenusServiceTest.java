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

import static ru.alvisid.votingsystem.TestData.MenuTestData.*;
import static ru.alvisid.votingsystem.TestData.TestData.*;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
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
        assertMatchIgnoringFields(service.getAll(), MENU_1, MENU_2, MENU_3, MENU_4, NEW_MENU);
    }

    @Test
    public void get() {
        Menu expectedMenu = MENU_1;
        Menu gotMenu = service.get(expectedMenu.getId());
        assertMatchIgnoringFields(gotMenu, expectedMenu, "restaurant", "price", "votes");

    }

    @Test
    public void getNotFound () {
        expectedException.expect(NotFoundException.class);
        service.get(1);
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
    public void update() {
        //realize in future
        Menu updateMenu = new Menu(MENU_1);
        updateMenu.setDate(LocalDate.now());
        service.update(updateMenu);
        assertMatchIgnoringFields(service.get(MENU_1.getId()), updateMenu);
    }

    @Test
    public void getAll() {
        List<Menu> all = service.getAll();
        assertMatchIgnoringFields(all, MENU_1, MENU_2, MENU_3, MENU_4);
    }

    @Test
    public void getBeetwen() {
        List<Menu> menusBeetwen = service.getBetween(MENU_1.getDate().minusDays(1), MENU_2.getDate().plusDays(1));
        assertMatchIgnoringFields(menusBeetwen, MENU_1, MENU_2);
    }

    @Test
    public void getPriceByID() {
        // implement in future
    }
}
