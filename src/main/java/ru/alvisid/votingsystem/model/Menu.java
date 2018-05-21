package ru.alvisid.votingsystem.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.internal.util.stereotypes.Immutable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.ALL_SORTED, query = "SELECT m FROM Menu m ORDER BY m.date, m.restaurant.name"),
        @NamedQuery(name = Menu.ALL_BEETWEN, query = "SELECT m FROM Menu m WHERE m.date>=?1 AND m.date<=?2")
})
@Entity
@Table(name = "menus",
        uniqueConstraints = @UniqueConstraint(columnNames = {"restaurants_id", "date"}, name = "menus_idx"))
public class Menu extends AbsractBaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String ALL_SORTED = "Menu.getAllSorted";
    public static final String ALL_BEETWEN = "Menu.getAllBeetwen";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurants_id")
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date")
    @NotNull
    @Immutable
    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "dish")
    @Column(name = "price")
    @CollectionTable(name = "prices", joinColumns = @JoinColumn(name = "menu_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "dish"}, name = "prices_idx"))
    @OrderBy("dish")
    private Map <String, Float> price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @Fetch(FetchMode.SUBSELECT)
    private List <Vote> votes;

    public Menu() {
        super();
    }

    public Menu(Restaurant restaurant, LocalDate date, Map <String, Float> price) {
        this.restaurant = restaurant;
        this.date = date;
        this.price = price;
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date, Map <String, Float> menu) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.price = menu;
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date, Map <String, Float> price, List <Vote> votes) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.price = price;
        this.votes = votes;
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getRestaurant(), menu.getDate(), menu.getMenu(), menu.getVotes());
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map <String, Float> getMenu() {
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
                " votes=" + votes +
                ')';
    }

    /*@Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        ArrayList<Vote> votes = new ArrayList <>(getVotes());
        ArrayList<Vote> expectVotes = new ArrayList <>(((Menu)o).getVotes());
        return votes.equals(expectVotes);
    }*/

    //del
    /*@Override
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
    }*/
}
