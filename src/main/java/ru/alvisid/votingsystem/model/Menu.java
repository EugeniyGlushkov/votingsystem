package ru.alvisid.votingsystem.model;

import java.time.LocalDate;
import java.util.Map;

public class Menu extends AbsractBaseEntity {
    private final Restaurant restaurant;
    private final LocalDate date;
    private final Map<String, Float> price;

    public Menu(Integer id, Restaurant restaurant, LocalDate date, Map<String, Float> menu) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.price = menu;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<String, Float> getMenu() {
        return price;
    }

    @Override
    public String toString() {
        return "Menu (" +
                "id=" + id +
                " date=" + date +
                " menu=" + price +
                ')';
    }
}
