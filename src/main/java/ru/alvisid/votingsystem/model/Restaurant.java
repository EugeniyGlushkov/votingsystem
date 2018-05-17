package ru.alvisid.votingsystem.model;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name")
})
@Entity
@Table(name = "restaurants", uniqueConstraints =
@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx"))
public class Restaurant extends AbstractNamedEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_SORTED = "Restaurant.getAllSorted";

    public Restaurant() {
        super();
    }

    public Restaurant(String name) {
        super(name);
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName());
    }

    @Override
    public String toString() {
        return "Restaurant (" +
                "id=" + id +
                " name=" + name +
                ')';
    }
}
