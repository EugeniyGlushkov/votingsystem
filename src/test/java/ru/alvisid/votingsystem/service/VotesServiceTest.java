package ru.alvisid.votingsystem.service;

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
import ru.alvisid.votingsystem.util.exception.NotFoundException;

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
        service.delete(VOTE_3.getId());
        //assertMatch(service);
    }

    @Test
    public void deleteNotFound() {
        expectedException.expect(NotFoundException.class);
        service.delete(999);
    }
}
