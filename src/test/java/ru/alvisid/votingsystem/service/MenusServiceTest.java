package ru.alvisid.votingsystem.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.votingsystem.TestData.MenuTestData;
import ru.alvisid.votingsystem.TestData.RestaurantTestData;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.mock.InMemoryMenusRepository;
import ru.alvisid.votingsystem.service.impl.MenusServiseImpl;
import ru.alvisid.votingsystem.util.MenuUtils;
import ru.alvisid.votingsystem.util.RestaurantUtils;
import ru.alvisid.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Arrays;

public class MenusServiceTest {
    private static ConfigurableApplicationContext appCtx;
    private static MenusServiseImpl service;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml" , "spring/mock.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        service = appCtx.getBean(MenusServiseImpl.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Test
    public void testCreate() {
        service.create(MenuUtils.getNewMenu(RestaurantTestData.rest_1, LocalDate.now(), MenuTestData.firstMenu));
    }

    @Test(expected = NotFoundException.class)
    public void testGet() {
        service.get(12);
    }
}
