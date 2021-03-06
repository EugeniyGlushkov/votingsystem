package ru.alvisid.votingsystem.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.hibernate.validator.internal.util.stereotypes.Immutable;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;


@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@SuppressWarnings("JpaQlInspection")//https://jazzy.id.au/2008/10/30/list_of_suppresswarnings_arguments.html
@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.ALL_SORTED, query = "SELECT m FROM Menu m ORDER BY m.date, m.restaurant.name"),
        @NamedQuery(name = Menu.ALL_BEETWEN, query = "SELECT m FROM Menu m WHERE m.date>=?1 AND m.date<=?2 ORDER BY m.date, m.restaurant.name")
})
@Entity
@Table(name = "menus",
        uniqueConstraints = @UniqueConstraint(columnNames = {"restaurants_id", "date"}, name = "menus_idx"))
public class Menu extends AbsractBaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String ALL_SORTED = "Menu.getAllSorted";
    public static final String ALL_BEETWEN = "Menu.getAllBeetwen";

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurants_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
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
    @NotNull
    private Map <String, Float> price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @NotNull
    private Set<Vote> votes;

    public Menu() {
        super();
    }

    public Menu(Restaurant restaurant, LocalDate date, Map <String, Float> price) {
        this(null, restaurant, date, price);
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date, Map <String, Float> menu) {
        this(id, restaurant, date, menu, new HashSet <Vote>());
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date, Map <String, Float> price, Set <Vote> votes) {
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

    public Set <Vote> getVotes() {
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

    public void setVotes(Set <Vote> votes) {
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


/*
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + restaurant.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }*/
}
