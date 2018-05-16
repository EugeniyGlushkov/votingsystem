package ru.alvisid.votingsystem.controller;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.web.menu.PriceRestController;

import java.util.Arrays;

public class PriceControllerTest {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static ConfigurableApplicationContext appCtx;
    private static PriceRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml" , "spring/mock.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(PriceRestController.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Test
    public void testGetPrice(){
        log.info("test GetPrice.");
        controller.getPrice(100007);
    }

    @Test(expected = NotFoundException.class)
    public void testGetPriceWithUnknownId() {
        log.info("test GetPriceWithUnknownId.");
        controller.getPrice(99999);
    }
}
