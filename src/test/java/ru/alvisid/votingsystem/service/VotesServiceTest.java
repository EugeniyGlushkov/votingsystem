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
import ru.alvisid.votingsystem.TestData.TestData;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.util.DateTimeUtil;
import ru.alvisid.votingsystem.util.VoteUtils;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.util.exception.OverTimeException;

import java.time.LocalDateTime;
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

    @Test
    public void create() {
        service.create(TestData.NEW_VOTE);
        List<Vote> atual = service.getAll();
        assertMatch(atual, Arrays.asList(VOTE_1, VOTE_3, VOTE_2, VOTE_4, NEW_TEST_VOTE));
    }

    @Test
    public void update() {
        DateTimeUtil.OVER_TIME = LocalDateTime.now().plusHours(1);
        Vote apdatedVote = new Vote (VOTE_4);
        apdatedVote.setMenu(MENU_3);
        service.update(apdatedVote);
        List<Vote> atual = service.getAll();
        assertMatch(atual, Arrays.asList(VOTE_1, VOTE_3, VOTE_2, apdatedVote));
    }

    @Test
    public void updateNotFound() {
        expectedException.expect(NotFoundException.class);
        DateTimeUtil.OVER_TIME = LocalDateTime.now().plusHours(1);
        Vote apdatedVote = new Vote (VOTE_4);
        apdatedVote.setId(999);
        service.update(apdatedVote);
    }

    @Test
    public void updateOverData() {
        expectedException.expect(OverTimeException.class);
        expectedException.expectMessage("The current date");
        DateTimeUtil.OVER_TIME = LocalDateTime.now().plusHours(1);
        Vote apdatedVote = new Vote (VOTE_3);
        service.update(apdatedVote);
    }

    @Test
    public void updateOverTime() {
        expectedException.expect(OverTimeException.class);
        expectedException.expectMessage("The current time");
        DateTimeUtil.OVER_TIME = LocalDateTime.now().minusHours(1);
        Vote apdatedVote = new Vote (VOTE_4);
        service.update(apdatedVote);
    }

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
        DateTimeUtil.OVER_TIME = LocalDateTime.now().plusHours(1);
        service.delete(VOTE_4.getId());
        List<Vote> actual = service.getAll();
        List<Vote> expected = Arrays.asList(VOTE_1, VOTE_3, VOTE_2);
        assertMatch(actual, expected);


        /*for (int i = 0, end = actual.size();i < end;i++) {
            System.out.println(actual.get(i));
            System.out.println(expected.get(i));
            assertMatch(actual.get(i).getMenu(), expected.get(i).getMenu());
        }*/
    }

    @Test
    public void deleteOverDate() {
        expectedException.expect(OverTimeException.class);
        expectedException.expectMessage("The current date");
        DateTimeUtil.OVER_TIME = LocalDateTime.now().plusHours(1);
        service.delete(VOTE_2.getId());
    }

    @Test
    public void deleteOverTime() {
        expectedException.expect(OverTimeException.class);
        expectedException.expectMessage("The current time");
        DateTimeUtil.OVER_TIME = LocalDateTime.now().minusHours(1);
        service.delete(VOTE_4.getId());
    }

    @Test
    public void deleteNotFound() {
        expectedException.expect(NotFoundException.class);
        service.delete(999);
    }

    @Test
    public void getAll() {
        List<Vote> atual = service.getAll();
        assertMatch(atual, Arrays.asList(VOTE_1, VOTE_3, VOTE_2, VOTE_4));
    }

    @Test
    public void getAllByUserId() {
        List<Vote> atual = service.getAllByUserId(USER_3.getId());
        assertMatch(atual, Arrays.asList(VOTE_3, VOTE_4));
    }

    @Test
    public void getAllByMenuId() {
        List<Vote> atual = service.getAllByRestaurantId(RESTAURANT_2.getId());
        assertMatch(atual, Arrays.asList(VOTE_3, VOTE_4));
    }


}
