package ru.alvisid.votingsystem.TestData;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class TestData {
    private static AtomicInteger idCounter = new AtomicInteger(100000);
    private static AtomicInteger voteIdCounter = new AtomicInteger(1000);

    public static int getNewId() {
        return idCounter.getAndIncrement();
    }

    public static int getNewVoteId() {
        return voteIdCounter.getAndIncrement();
    }

    private TestData() {}

    public static User USER_1 = UserTestData.user_1;
    public static User USER_2 = UserTestData.user_2;
    public static User USER_3 = UserTestData.user_3;
    public static User NEW_USER = UserTestData.new_user;

    public static Restaurant RESTAURANT_1 = RestaurantTestData.rest_1;
    public static Restaurant RESTAURANT_2 = RestaurantTestData.rest_2;
    public static Restaurant RESTAURANT_3 = RestaurantTestData.rest_3;
    public static Restaurant NEW_RESTAURANT = RestaurantTestData.rest_new;

    public static Menu MENU_1 = MenuTestData.menu_1;
    public static Menu MENU_2 = MenuTestData.menu_2;
    public static Menu MENU_3 = MenuTestData.menu_3;
    public static Menu MENU_4 = MenuTestData.menu_4;
    public static Menu NEW_MENU = MenuTestData.new_menu;
    public static List<Menu> MENUS = MenuTestData.MENUS;

    public static Vote VOTE_1 = VoteTestData.vote_1;
    public static Vote VOTE_2 = VoteTestData.vote_2;
    public static Vote VOTE_3 = VoteTestData.vote_3;
    public static Vote VOTE_4 = VoteTestData.vote_4;

    public static Vote NEW_VOTE = VoteTestData.new_vote;
    public static Vote NEW_TEST_VOTE = VoteTestData.new_test_vote;


    static {
        USER_1.setVotes(VoteTestData.voteUserSet_1);
        USER_2.setVotes(VoteTestData.voteUserSet_2);
        USER_3.setVotes(VoteTestData.voteUserSet_3);

        MENU_1.setVotes(VoteTestData.voteMenuSet_1);
        MENU_2.setVotes(VoteTestData.voteMenuSet_2);
        MENU_3.setVotes(VoteTestData.voteMenuSet_3);
        MENU_4.setVotes(VoteTestData.voteMenuSet_4);
    }

    public static  <T> void assertMatch(T actual, T expected, String... ignoringFields) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, ignoringFields);
    }

    public static  <T> void assertMatch(Iterable <T> actual, Iterable <T> expected, String... ignoringFields) {
        assertThat(actual).usingElementComparatorIgnoringFields(ignoringFields).isEqualTo(expected);
    }
}
