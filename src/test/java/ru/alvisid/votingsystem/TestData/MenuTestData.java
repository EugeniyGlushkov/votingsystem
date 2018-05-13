package ru.alvisid.votingsystem.TestData;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.Restaurant;
import ru.alvisid.votingsystem.util.MenuUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MenuTestData {
    private static AtomicInteger idCounter = new AtomicInteger(100000);

    public static final Map<String, Float> firstMenu = new HashMap<>();
    public static final Map <String, Float> secondMenu = new HashMap <>();
    public static final List<Menu> MENUS;

    public static final Menu menu_1;
    public static final Menu menu_2;
    public static final Menu menu_3;
    public static final Menu menu_4;

    public static final Menu new_menu;

    static {
        firstMenu.put("cake", 12F);
        firstMenu.put("fish", 5.4F);
        firstMenu.put("cheaps", 4.56F);
        secondMenu.put("soup", 3.86F);
        secondMenu.put("rooster", 8.96F);
        secondMenu.put("eggs", 12.2F);
        secondMenu.put("rabbit", 4.3F);

        menu_1 = getMenu(RestaurantTestData.rest_1, LocalDate.of(2018, 5, 9), firstMenu);
        menu_2 = getMenu(RestaurantTestData.rest_2, LocalDate.of(2018, 5, 9), secondMenu);
        menu_3 = getMenu(RestaurantTestData.rest_1, LocalDate.now(), secondMenu);
        menu_4 = getMenu(RestaurantTestData.rest_2, LocalDate.now(), firstMenu);

        new_menu = MenuUtils.getNewMenu(RestaurantTestData.rest_3, LocalDate.now(), firstMenu);

        menu_1.setVotes(Arrays.asList(VoteTestData.vote_1));
        menu_2.setVotes(Arrays.asList(VoteTestData.vote_2, VoteTestData.vote_3));
        menu_4.setVotes(Arrays.asList(VoteTestData.vote_4));

        MENUS = Arrays.asList(
                menu_1,
                menu_2,
                menu_3,
                menu_4
        );
    }

    private MenuTestData(){}

    public static Menu getMenu(Restaurant restaurant, LocalDate date, Map <String, Float> menu) {
        return new Menu(idCounter.incrementAndGet(), restaurant, date, menu);
    }

    public static AtomicInteger getIdCounter() {
        return idCounter;
    }
}
