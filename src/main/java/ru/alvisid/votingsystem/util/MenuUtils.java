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



    /*public static Menu getZeroMenu() {
        Menu zeroMenu = new Menu(null, null, null);
        zeroMenu.setId(0);
        return zeroMenu;
    }*/



    public static PriceTransfer getPriceForTransfer(Menu menu) {
        return new PriceTransfer(menu.getRestaurant().getName(), menu.getDate(), menu.getMenu());
    }
}
