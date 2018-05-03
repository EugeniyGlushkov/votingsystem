package ru.alvisid.votingsystem.util;

import org.omg.CORBA.PUBLIC_MEMBER;
import ru.alvisid.votingsystem.TO.VoteWithSumVotes;
import ru.alvisid.votingsystem.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Utils {
    private static AtomicInteger idCounter = new AtomicInteger(0);
    public static final Map<String, Float> firstMenu=new HashMap<>();
    public static final Map<String, Float> secondMenu=new HashMap<>();
    public static final List<Vote> VOTES;
    static {
        firstMenu.put("cake", 12F);
        firstMenu.put("fish", 5.4F);
        firstMenu.put("cheaps", 4.56F);
        secondMenu.put("soup", 3.86F);
        secondMenu.put("rooster", 8.96F);
        secondMenu.put("eggs", 12.2F);
        secondMenu.put("rabbit", 4.3F);
        VOTES = Arrays.asList(
                new Vote(getNewUser("Alex", Role.ROLE_USER), getNewMenu(getNewRestaurant("Ambassador"), LocalDate.of(2018, 5, 1), firstMenu)),
                new Vote(getNewUser("Sindy", Role.ROLE_USER, Role.ROLE_ADMIN), getNewMenu(getNewRestaurant("Mandalay"), LocalDate.of(2018, 5, 1), secondMenu)),
                new Vote(getNewUser("Fox", Role.ROLE_USER), getNewMenu(getNewRestaurant("Mandalay"), LocalDate.of(2018, 5, 1), secondMenu))
        );
    }



    private Utils() {
    }

    public static User getNewUser(String name, Set<Role> roles) {
        return new User(idCounter.incrementAndGet(), name, roles);
    }

    public static User getNewUser(String name, Role role, Role... roles) {
        return new User(idCounter.incrementAndGet(), name, role, roles);
    }

    public static Menu getNewMenu(Restaurant restaurant, LocalDate date, Map<String, Float> menu) {
        return new Menu(idCounter.incrementAndGet(), restaurant, date, menu);
    }

    public static Restaurant getNewRestaurant(String name) {
        return new Restaurant(idCounter.incrementAndGet(), name);
    }

    public static List<VoteWithSumVotes> getVotesWithSumVotes(List<Vote> votes) {
        System.out.println(votes);
        Map<LocalDate, Integer> voutesADay = new HashMap<>();
        Map<LocalDate, Map<Restaurant, Integer>> voutesRestADay = new HashMap<>();

        votes.forEach(vote -> {
                    voutesADay.merge(vote.getMenu().getDate(), 1, Integer::sum);
                    LocalDate currDate = vote.getMenu().getDate();
                    Map<Restaurant, Integer> oldMap = voutesRestADay.get(currDate);
                    if (oldMap == null) {
                        oldMap = new HashMap<>();
                        oldMap.put(vote.getMenu().getRestaurant(), 1);
                        voutesRestADay.put(currDate, oldMap);
                    } else {
                        voutesRestADay.get(currDate).merge(vote.getMenu().getRestaurant(), 1, Integer::sum);
                    }
                });

        System.out.println(voutesADay);
        System.out.println(voutesRestADay);

        return votes.stream().map(vote -> new VoteWithSumVotes(
                vote.getUser(),
                vote.getMenu(),
                voutesRestADay.get(vote.getMenu().getDate()).get(vote.getMenu().getRestaurant()),
                voutesADay.get(vote.getMenu().getDate())
        )).collect(Collectors.toList());
    }
}
