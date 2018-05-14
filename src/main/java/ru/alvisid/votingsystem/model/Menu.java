package ru.alvisid.votingsystem.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Menu extends AbsractBaseEntity {
    private Restaurant restaurant;
    private LocalDate date;
    private Map<String, Float> price;
    private List<Vote> votes;

    public Menu() {
    }

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

    public Menu(Integer id, Restaurant restaurant, LocalDate date, Map<String, Float> price, List<Vote> votes) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.price = price;
        this.votes = votes;
    }

    public Menu (Menu menu) {
        this(menu.getId(), menu.getRestaurant(), menu.getDate(), menu.getMenu(), menu.getVotes());
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

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPrice(Map <String, Float> price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Menu menu = (Menu) o;

        if (!restaurant.equals(menu.restaurant)) return false;
        if (!date.equals(menu.date)) return false;
        return price.equals(menu.price);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + restaurant.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
