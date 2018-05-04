package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.to.RestaurantVotes;
import ru.alvisid.votingsystem.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Utils {
    private static AtomicInteger idCounter = new AtomicInteger(100000);

    public static final Map <String, Float> firstMenu = new HashMap <>();
    public static final Map <String, Float> secondMenu = new HashMap <>();
    public static final List <Menu> MENUS;
    public static final Restaurant rest_1 = getNewRestaurant("Ambassador");
    public static final Restaurant rest_2 = getNewRestaurant("Mandalay");
    public static final Vote vote_1;
    public static final Vote vote_2;
    public static final Vote vote_3;
    public static final Vote vote_4;
    public static final Menu menu_1;
    public static final Menu menu_2;
    public static final Menu menu_3;
    public static final Menu menu_4;

    static {
        firstMenu.put("cake", 12F);
        firstMenu.put("fish", 5.4F);
        firstMenu.put("cheaps", 4.56F);
        secondMenu.put("soup", 3.86F);
        secondMenu.put("rooster", 8.96F);
        secondMenu.put("eggs", 12.2F);
        secondMenu.put("rabbit", 4.3F);

        menu_1 = getNewMenu(rest_1, LocalDate.of(2018, 5, 1), firstMenu);
        menu_2 = getNewMenu(rest_2, LocalDate.of(2018, 5, 1), secondMenu);
        menu_3 = getNewMenu(rest_1, LocalDate.of(2018, 5, 2), secondMenu);
        menu_4 = getNewMenu(rest_2, LocalDate.of(2018, 5, 2), firstMenu);

        vote_1 = new Vote(getNewUser("Alex", Role.ROLE_USER), menu_1);
        vote_2 = new Vote(getNewUser("Sindy", Role.ROLE_USER, Role.ROLE_ADMIN), menu_2);
        vote_3 = new Vote(getNewUser("Fox", Role.ROLE_USER), menu_3);
        vote_4 = new Vote(getNewUser("Fox", Role.ROLE_USER), menu_4);

        menu_1.setVotes(Arrays.asList(vote_1));
        menu_2.setVotes(Arrays.asList(vote_2, vote_3));
        menu_4.setVotes(Arrays.asList(vote_4));

        MENUS = Arrays.asList(
                menu_1,
                menu_2,
                menu_3,
                menu_4
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

    public static List <RestaurantVotes> getRestaurantVotes(List <Menu> menus) {
        Map <LocalDate, Integer> voutesADay = new HashMap <>();

        menus.forEach(menu -> voutesADay.merge(menu.getDate(), Objects.isNull(menu.getVotes()) ? 0 : menu.getVotes().size(), Integer::sum));

        return menus.stream()
                .map(menu -> new RestaurantVotes(
                        menu.getRestaurant().getId(),
                        menu.getRestaurant().getName(),
                        menu.getDate(),
                        Objects.isNull(menu.getVotes()) ? 0 : menu.getVotes().size(),
                        voutesADay.get(menu.getDate())))
                .collect(Collectors.toList());
    }
}
