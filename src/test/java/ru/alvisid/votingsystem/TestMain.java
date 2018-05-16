package ru.alvisid.votingsystem;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.votingsystem.TestData.MenuTestData;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;
import ru.alvisid.votingsystem.repository.jdbc.JdbcMenusRepositoryImpl;

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
