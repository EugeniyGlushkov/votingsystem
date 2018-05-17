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

import static ru.alvisid.votingsystem.TestData.TestData.*;
import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.List;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantsServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private RestaurantsService service;

    @Test
    public void get() {
        Restaurant expectedRestaurant = RESTAURANT_1;
        Restaurant actualRestaurant = service.get(expectedRestaurant.getId());
        assertMatch(actualRestaurant, expectedRestaurant);
    }

    @Test
    public void getNotFound() {
        expectedException.expect(NotFoundException.class);
        service.get(MENU_1.getId());
    }

    @Test
    public void create() {

    }

    @Test
    public void getAll() {
        List<Restaurant> actualRestaurants = service.getAll();
        assertMatch(actualRestaurants, RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);
    }
}
