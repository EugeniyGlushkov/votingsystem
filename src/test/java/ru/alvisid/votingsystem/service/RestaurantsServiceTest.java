package ru.alvisid.votingsystem.service;

import org.junit.AfterClass;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.alvisid.votingsystem.TestData.TestData.*;

import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.TimeUnit;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantsServiceTest{
    private static final Logger log = getLogger("result");

    private static StringBuilder results = new StringBuilder();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    static {
        SLF4JBridgeHandler.install();
    }

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                 "\nTest                 Duration, ms" +
                 "\n---------------------------------" +
                 results +
                 "\n---------------------------------");
    }

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
        service.create(NEW_RESTAURANT);
        assertMatch(service.getAll(), Arrays.asList(RESTAURANT_1, RESTAURANT_2, NEW_RESTAURANT, RESTAURANT_3));
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT_2.getId());
        assertMatch(service.getAll(), Arrays.asList(RESTAURANT_1, RESTAURANT_3));
    }

    @Test
    public void deleteNotFound() {
        expectedException.expect(NotFoundException.class);
        service.delete(MENU_1.getId());
    }

    @Test
    public void update() {
        Restaurant expectedRestaurant = new Restaurant(RESTAURANT_1);
        expectedRestaurant.setName("Updated_name");
        service.update(expectedRestaurant);
        assertMatch(service.get(expectedRestaurant.getId()), expectedRestaurant);
    }

    @Test
    public void updateNotFound() {
        expectedException.expect(NotFoundException.class);
        Restaurant expectedRestaurant = new Restaurant(RESTAURANT_1);
        expectedRestaurant.setId(1);
        service.update(expectedRestaurant);
    }

    @Test
    public void getAll() {
        List <Restaurant> actualRestaurants = service.getAll();
        assertMatch(actualRestaurants, Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3));
    }
}
