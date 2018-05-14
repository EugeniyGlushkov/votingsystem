package ru.alvisid.votingsystem.service;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import ru.alvisid.votingsystem.TestData.MenuTestData;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

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
        Menu expectedMenu = new Menu(MenuTestData.new_menu);
        Menu createdMenu = service.create(MenuTestData.new_menu);
        expectedMenu.setId(createdMenu.getId());
        assertThat(expectedMenu).isEqualTo(createdMenu);
    }

    @Test
    public void get() {
        Menu expectedMenu = MenuTestData.menu_1;
        expectedMenu.setId(100006);
        Menu gotMenu = service.get(expectedMenu.getId());
        assertThat(expectedMenu).isEqualToIgnoringGivenFields(gotMenu, "restaurant", "price", "votes");

    }

    @Test
    public void delete() {
        service.delete(100006);
    }

    @Test()
    public void deleteNotFoundExc() {
        expectedException.expect(NotFoundException.class);
        service.delete(100002);
    }
}
