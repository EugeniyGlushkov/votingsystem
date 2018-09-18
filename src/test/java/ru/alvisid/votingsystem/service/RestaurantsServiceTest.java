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

import static org.slf4j.LoggerFactory.getLogger;
import static ru.alvisid.votingsystem.TestData.TestData.*;

import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.repository.JpaUtil;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RestaurantsServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantsService service;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        cacheManager.getCache("restaurants").clear();
    }

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
        List<Restaurant> actualRestaurants = service.getAll();
        assertMatch(actualRestaurants, Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3));
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new Restaurant(null,"Q"))
                , ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Restaurant(null,null))
                , ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Restaurant(null,"qqqqqqqqqqwwwwwwwwww" +
                "qqqqqqqqqqwwwwwwwwww" +
                "qqqqqqqqqqwwwwwwwwww" +
                "qqqqqqqqqqwwwwwwwwww" +
                "qqqqqqqqqqwwwwwwwwww" +
                "a"))
                , ConstraintViolationException.class);
    }
}
