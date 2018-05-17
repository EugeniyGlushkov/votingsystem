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

import static ru.alvisid.votingsystem.TestData.TestData.MENU_1;
import static ru.alvisid.votingsystem.TestData.TestData.VOTE_1;
import static ru.alvisid.votingsystem.TestData.TestData.assertMatch;

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
        Vote expectedVote = VOTE_1;
        Vote actualVote = service.get(expectedVote.getUser().getId(), expectedVote.getMenu().getId());
        System.out.println(actualVote);
        //assertMatch(actualMenu, expectedMenu, "restaurant", "price", "votes");
    }
}
