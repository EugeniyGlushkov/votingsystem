package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.to.RestaurantVotes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class VoteUtils {

    private VoteUtils() {
    }

    public static Vote getNewVote(User user, Menu menu) {
        return new Vote(null, user, menu);
    }

    public static List<RestaurantVotes> getRestaurantVotes(List<Menu> menus) {
        return getFilteredRestaurantVotes(menus, menu -> true);
    }

    public static List<RestaurantVotes> getFilteredRestaurantVotes(List<Menu> menus, LocalDate startDate, LocalDate endDate) {
        return getFilteredRestaurantVotes(menus, menu -> DateTimeUtil.isBetween(menu.getDate(), startDate, endDate));
    }

    public static List<RestaurantVotes> getFilteredRestaurantVotes(List<Menu> menus, Predicate<Menu> filter) {
        /*Map<LocalDate, Integer> votesADay = new HashMap<>();

        menus.forEach(menu -> votesADay.merge(menu.getDate(), Objects.isNull(menu.getVotes()) ? 0 : menu.getVotes().size(), Integer::sum));*/

        Map<LocalDate, Integer> votesADay = menus.stream()
                .collect(
                        Collectors.groupingBy(Menu::getDate
                                , Collectors.summingInt(menu -> Objects.isNull(menu.getVotes()) ? 0 : menu.getVotes().size()))
                );

        return menus.stream()
                .filter(filter)
                .map(menu -> createRestaurantVotes(menu, votesADay.get(menu.getDate())))
                .collect(Collectors.toList());
    }


    private static RestaurantVotes createRestaurantVotes(Menu menu, Integer votesADay) {
        return new RestaurantVotes(menu.getId()
                , menu.getRestaurant().getId()
                , menu.getRestaurant().getName()
                , menu.getDate()
                , Objects.isNull(menu.getVotes()) ? 0 : menu.getVotes().size()
                , votesADay);
    }
}
