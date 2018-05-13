package ru.alvisid.votingsystem;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.votingsystem.repository.MenusRepository;
import ru.alvisid.votingsystem.repository.jdbc.JdbcMenusRepositoryImpl;

import java.util.Arrays;

//del
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
        MenusRepository repository = appCtx.getBean(JdbcMenusRepositoryImpl.class);
        appCtx.close();
    }
}
