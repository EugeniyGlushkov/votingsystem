package ru.alvisid.votingsystem.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.web.menu.PriceRestController;

@ContextConfiguration({"classpath:spring/spring-app.xml" , "classpath:spring/mock.xml"})
@RunWith(SpringRunner.class)
public class PriceControllerSpringTest {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PriceRestController controller;

    @Test
    public void testGetPrice(){
        log.info("test GetPrice.");
        controller.getPrice(100003);
    }

    @Test(expected = NotFoundException.class)
    public void testGetPriceWithUnknownId() {
        log.info("test GetPriceWithUnknownId.");
        controller.getPrice(99999);
    }
}
