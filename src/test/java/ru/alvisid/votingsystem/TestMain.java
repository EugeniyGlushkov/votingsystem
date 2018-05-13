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
        /*ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring/mock.xml", "spring/spring-app.xml");
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        System.out.println(MenuTestData.MENUS);
        ctx.close();*/
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml");
        System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
        MenusRepository repository = appCtx.getBean(JdbcMenusRepositoryImpl.class);
        Menu newMenu = MenuTestData.new_menu;
        System.out.println(newMenu);
        repository.save(newMenu);
        appCtx.close();
    }
}
