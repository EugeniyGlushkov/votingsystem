package ru.alvisid.votingsystem;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.votingsystem.TestData.MenuTestData;

import java.util.Arrays;

//del
public class TestMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring/mock.xml", "spring/spring-app.xml");
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        System.out.println(MenuTestData.MENUS);
        ctx.close();
    }
}
