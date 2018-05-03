package ru.alvisid.votingsystem.util;

import org.omg.CORBA.PUBLIC_MEMBER;
import ru.alvisid.votingsystem.TO.VoteWithSumVotes;
import ru.alvisid.votingsystem.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Utils {
    private static AtomicInteger idCounter = new AtomicInteger(100000);
    public static final Map <String, Float> firstMenu = new HashMap <>();
    public static final Map <String, Float> secondMenu = new HashMap <>();
    public static final List <Vote> VOTES;
    public static final Restaurant rest_1 = getNewRestaurant("Ambassador");
    public static final Restaurant rest_2 = getNewRestaurant("Mandalay");

    static {
        firstMenu.put("cake", 12F);
        firstMenu.put("fish", 5.4F);
        firstMenu.put("cheaps", 4.56F);
        secondMenu.put("soup", 3.86F);
        secondMenu.put("rooster", 8.96F);
        secondMenu.put("eggs", 12.2F);
        secondMenu.put("rabbit", 4.3F);
        VOTES = Arrays.asList(
                new Vote(getNewUser("Alex", Role.ROLE_USER), getNewMenu(rest_1, LocalDate.of(2018, 5, 1), firstMenu)),
                new Vote(getNewUser("Sindy", Role.ROLE_USER, Role.ROLE_ADMIN), getNewMenu(rest_2, LocalDate.of(2018, 5, 1), secondMenu)),
                new Vote(getNewUser("Fox", Role.ROLE_USER), getNewMenu(rest_2, LocalDate.of(2018, 5, 1), secondMenu)),
                new Vote(getNewUser("Fox", Role.ROLE_USER), getNewMenu(rest_2, LocalDate.of(2018, 5, 2), firstMenu))
        );
    }


    private Utils() {
    }

    public static User getNewUser(String name, Set <Role> roles) {
        return new User(idCounter.incrementAndGet(), name, roles);
    }

    public static User getNewUser(String name, Role role, Role... roles) {
        return new User(idCounter.incrementAndGet(), name, role, roles);
    }

    public static Menu getNewMenu(Restaurant restaurant, LocalDate date, Map <String, Float> menu) {
        return new Menu(idCounter.incrementAndGet(), restaurant, date, menu);
    }

    public static Restaurant getNewRestaurant(String name) {
        return new Restaurant(idCounter.incrementAndGet(), name);
    }

    public static List <VoteWithSumVotes> getVotesWithSumVotes(List <Vote> votes) {
        Map <LocalDate, Integer> voutesADay = new HashMap <>();
        Map <LocalDate, Map <Restaurant, Integer>> voutesRestADay = new HashMap <>();

        votes.forEach(vote -> {
            LocalDate currDate = vote.getMenu().getDate();
            Restaurant currRest = vote.getMenu().getRestaurant();
            Map <Restaurant, Integer> oldMap = voutesRestADay.get(currDate);
            voutesADay.merge(currDate, 1, Integer::sum);
            if (oldMap == null) {
                oldMap = new HashMap <>();
                oldMap.put(currRest, 1);
                voutesRestADay.put(currDate, oldMap);
            } else {
                oldMap.merge(currRest, 1, Integer::sum);
            }
        });

        return votes.stream().map(vote -> new VoteWithSumVotes(
                vote.getUser(),
                vote.getMenu(),
                voutesRestADay.get(vote.getMenu().getDate()).get(vote.getMenu().getRestaurant()),
                voutesADay.get(vote.getMenu().getDate())
        )).collect(Collectors.toList());
    }
}
