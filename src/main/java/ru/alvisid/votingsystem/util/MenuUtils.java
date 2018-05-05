package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.to.RestaurantVotes;
import ru.alvisid.votingsystem.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MenuUtils {
    private static AtomicInteger idCounter = new AtomicInteger(100000);

    public static final Map <String, Float> firstMenu = new HashMap <>();
    public static final Map <String, Float> secondMenu = new HashMap <>();
    public static final List <Menu> MENUS;

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

        menu_1 = getNewMenu(RestaurantUtils.rest_1, LocalDate.of(2018, 5, 1), firstMenu);
        menu_2 = getNewMenu(RestaurantUtils.rest_2, LocalDate.of(2018, 5, 1), secondMenu);
        menu_3 = getNewMenu(RestaurantUtils.rest_1, LocalDate.of(2018, 5, 2), secondMenu);
        menu_4 = getNewMenu(RestaurantUtils.rest_2, LocalDate.of(2018, 5, 2), firstMenu);

        menu_1.setVotes(Arrays.asList(VoteUtils.vote_1));
        menu_2.setVotes(Arrays.asList(VoteUtils.vote_2, VoteUtils.vote_3));
        menu_4.setVotes(Arrays.asList(VoteUtils.vote_4));

        MENUS = Arrays.asList(
                menu_1,
                menu_2,
                menu_3,
                menu_4
        );
    }


    private MenuUtils() {
    }

    public static Menu getNewMenu(Restaurant restaurant, LocalDate date, Map <String, Float> menu) {
        return new Menu(idCounter.incrementAndGet(), restaurant, date, menu);
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

    public static AtomicInteger getIdCounter() {
        return idCounter;
    }
}
