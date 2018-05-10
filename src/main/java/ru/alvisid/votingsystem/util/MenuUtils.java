package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.to.PriceTransfer;
import ru.alvisid.votingsystem.to.RestaurantVotes;
import ru.alvisid.votingsystem.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MenuUtils {
    private MenuUtils() {
    }

    public static Menu getNewMenu(Restaurant restaurant, LocalDate date, Map<String, Float> price) {
        return new Menu(restaurant, date, price);
    }

    public static List <RestaurantVotes> getRestaurantVotes(List <Menu> menus) {
        Map <LocalDate, Integer> voutesADay = new HashMap <>();

        menus.forEach(menu -> voutesADay.merge(menu.getDate(), Objects.isNull(menu.getVotes()) ? 0 : menu.getVotes().size(), Integer::sum));

        return menus.stream()
                .map(menu -> new RestaurantVotes(
                        menu.getId(),
                        menu.getRestaurant().getId(),
                        menu.getRestaurant().getName(),
                        menu.getDate(),
                        Objects.isNull(menu.getVotes()) ? 0 : menu.getVotes().size(),
                        voutesADay.get(menu.getDate())
                )).collect(Collectors.toList());
    }

    public static PriceTransfer getPriceForTransfer(Menu menu) {
        return new PriceTransfer(menu.getRestaurant().getName(), menu.getDate(), menu.getMenu());
    }
}
