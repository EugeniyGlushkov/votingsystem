package ru.alvisid.votingsystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alvisid.votingsystem.util.MenuUtils;
import ru.alvisid.votingsystem.util.UserUtils;

//del
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
    /*    log.debug("main");
        log.debug("main");
        log.debug("main");
        log.debug("main");
        log.debug("main");
        log.debug("main");
        log.debug("main");
        log.debug("main");
        log.debug("main");
        log.debug("main");
        log.debug("main");
        System.out.println(new Restaurant(0, "Ancor"));
        Map<String, Float> price = new HashMap<>();
        price.put("cake", 5.6F);
        price.put("soup", 3.2F);
        price.put("fish", 10.7F);
        User user = new User(3, "Poul", Role.ROLE_ADMIN, Role.ROLE_USER);
        Menu menu = new Menu(1, new Restaurant(2, "Coma"), LocalDate.now(), price);
        System.out.println(menu);
        System.out.println(user);
        System.out.println(new Vote(user, menu));*/
        //System.out.println(new InMemoryMenusRepository().getBetween(LocalDate.MIN, LocalDate.MAX));
        System.out.println(MenuUtils.getRestaurantVotes(MenuUtils.MENUS));
        System.out.println(UserUtils.USERS);
    }
}
