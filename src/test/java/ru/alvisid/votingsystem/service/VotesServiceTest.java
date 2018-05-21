package ru.alvisid.votingsystem.service;

import ch.qos.logback.core.util.TimeUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.util.DateTimeUtil;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.util.exception.OverTimeException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.alvisid.votingsystem.TestData.TestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VotesServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private VotesService service;

    //@Test



    @Test
    public void get() {
        Vote expectedVote = VOTE_2;
        Vote actualVote = service.get(expectedVote.getId());
        System.out.println(actualVote);
        assertMatch(actualVote, expectedVote/*, "user", "menu"*/);
    }

    @Test
    public void getNotFound() {
        expectedException.expect(NotFoundException.class);
        service.get(MENU_1.getId());
    }


    @Test
    public void delete() {
        DateTimeUtil.OVER_TIME = LocalTime.now().plusHours(1);
        service.delete(VOTE_2.getId());
        List <Vote> actual = service.getAll();
        List <Vote> expected = Arrays.asList(VOTE_1, VOTE_3, VOTE_4);
        assertMatch(actual, expected);


        /*for (int i = 0, end = actual.size();i < end;i++) {
            System.out.println(actual.get(i));
            System.out.println(expected.get(i));
            assertMatch(actual.get(i).getMenu(), expected.get(i).getMenu());
        }*/
    }

    @Test
    public void deleteOverTime() {
        expectedException.expect(OverTimeException.class);
        service.delete(VOTE_2.getId());
    }

    @Test
    public void deleteNotFound() {
        expectedException.expect(NotFoundException.class);
        service.delete(999);
    }

    @Test
    public void getAll() {
        List <Vote> atual = service.getAll();
        assertMatch(atual, Arrays.asList(VOTE_1, VOTE_3, VOTE_2, VOTE_4));
    }
}
