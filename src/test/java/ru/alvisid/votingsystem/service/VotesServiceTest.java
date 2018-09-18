package ru.alvisid.votingsystem.service;

import ch.qos.logback.core.util.TimeUtil;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.alvisid.votingsystem.TestData.TestData;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.Role;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.JpaUtil;
import ru.alvisid.votingsystem.util.DateTimeUtil;
import ru.alvisid.votingsystem.util.VoteUtils;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.util.exception.OverTimeException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.alvisid.votingsystem.TestData.TestData.*;

public class VotesServiceTest extends AbstractServiceTest {

    @Autowired
    private VotesService service;

    @Test
    public void create() {
        service.create(NEW_VOTE, NEW_VOTE.getUser().getId(), NEW_VOTE.getMenu().getId());
        List <Vote> atual = service.getAll();
        assertMatch(atual, Arrays.asList(VOTE_1, VOTE_3, VOTE_2, VOTE_4, NEW_TEST_VOTE));
    }

    @Test
    public void update() {
        DateTimeUtil.OVER_TIME = LocalDateTime.now().plusHours(1);
        Vote updatedVote = new Vote(VOTE_4);
        updatedVote.setUser(null);
        updatedVote.setMenu(null);
        service.update(updatedVote, VOTE_4.getUser().getId(), MENU_3.getId());
        List <Vote> actual = service.getAll();
        Set<Vote> votes = new HashSet<>();
        votes.add(VOTE_4);
        Menu expectMenu = new Menu(MENU_3);
        expectMenu.setVotes(votes);
        updatedVote.setMenu(expectMenu);
        assertMatch(service.get(VOTE_4.getId()), updatedVote);
        assertMatch(actual, Arrays.asList(VOTE_1, VOTE_3, VOTE_2, updatedVote));
    }

    @Test
    public void updateNotFound() {
        expectedException.expect(NotFoundException.class);
        DateTimeUtil.OVER_TIME = LocalDateTime.now().plusHours(1);
        Vote updatedVote = new Vote(VOTE_4);
        updatedVote.setId(999);
        service.update(updatedVote, updatedVote.getUser().getId(), updatedVote.getMenu().getId());
    }

    @Test
    public void updateOverData() {
        expectedException.expect(OverTimeException.class);
        expectedException.expectMessage("The current date");
        DateTimeUtil.OVER_TIME = LocalDateTime.now().plusHours(1);
        Vote updatedVote = new Vote(VOTE_3);
        service.update(updatedVote, updatedVote.getUser().getId(), updatedVote.getMenu().getId());
    }

    @Test
    public void updateOverTime() {
        expectedException.expect(OverTimeException.class);
        expectedException.expectMessage("The current time");
        DateTimeUtil.OVER_TIME = LocalDateTime.now().minusHours(1);
        Vote updatedVote = new Vote(VOTE_4);
        service.update(updatedVote, updatedVote.getUser().getId(), updatedVote.getMenu().getId());
    }

    @Test
    public void get() {
        Vote expectedVote = VOTE_2;
        Vote actualVote = service.get(expectedVote.getId());
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
        List <Vote> actual = service.getAll();
        List <Vote> expected = Arrays.asList(VOTE_1, VOTE_3, VOTE_2);
        assertMatch(actual, expected);
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
        List <Vote> atual = service.getAll();
        assertMatch(atual, Arrays.asList(VOTE_1, VOTE_3, VOTE_2, VOTE_4));
    }

    @Test
    public void getAllByUserId() {
        List <Vote> atual = service.getAllByUserId(USER_3.getId());
        assertMatch(atual, Arrays.asList(VOTE_3, VOTE_4));
    }

    @Test
    public void getAllByRestaurantId() {
        List <Vote> atual = service.getAllByRestaurantId(RESTAURANT_2.getId());
        assertMatch(atual, Arrays.asList(VOTE_3, VOTE_2));
    }

    /*@Test
    public void testValidation() {
        validateRootCause(() -> service.create(new Vote(null, null, MENU_4), MENU_3.getId(), MENU_4.getId())
                , ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Vote(), USER_3.getId(), USER_3.getId())
                , ConstraintViolationException.class);
    }*/
}
