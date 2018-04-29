package ru.alvisid.votingsystem;

import ru.alvisid.votingsystem.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Restaurant(0, "Ancor"));
        Map<String, Float> price = new HashMap<>();
        price.put("cake", 5.6F);
        price.put("soup", 3.2F);
        price.put("fish", 10.7F);
        User user = new User(3, "Poul", Role.ROLE_ADMIN, Role.ROLE_USER);
        Menu menu = new Menu(1, new Restaurant(2, "Coma"), LocalDate.now(), price);
        System.out.println(menu);
        System.out.println(user);
        System.out.println(new Vote(user, menu));
    }
}