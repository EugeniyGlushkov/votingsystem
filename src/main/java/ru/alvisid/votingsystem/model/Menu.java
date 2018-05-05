package ru.alvisid.votingsystem.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Menu extends AbsractBaseEntity {
    private final Restaurant restaurant;
    private final LocalDate date;
    private final Map<String, Float> price;
    protected List<Vote> votes;

    public Menu(Restaurant restaurant, LocalDate date, Map<String, Float> price) {
        this.restaurant = restaurant;
        this.date = date;
        this.price = price;
    }

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

    public List <Vote> getVotes() {
        return votes;
    }

    public void setVotes(List <Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Menu (" +
                "id=" + id +
                " restaurant=" + restaurant +
                " date=" + date +
                " menu=" + price +
                ')';
    }
}
