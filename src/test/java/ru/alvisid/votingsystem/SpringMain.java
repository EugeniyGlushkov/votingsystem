package ru.alvisid.votingsystem;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.alvisid.votingsystem.repository.MenusRepository;
import ru.alvisid.votingsystem.repository.jdbc.JdbcMenusRepositoryImpl;
import ru.alvisid.votingsystem.repository.mock.InMemoryMenusRepository;

import java.util.Arrays;
import java.util.Objects;

//del
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
        for (String s : appCtx.getBeanDefinitionNames()) {
            System.out.println(s);
        }
        //MenusRepository repository = appCtx.getBean(InMemoryMenusRepository.class);
        appCtx.close();
    }
}
