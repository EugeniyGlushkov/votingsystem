package ru.alvisid.votingsystem.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "restaurants", uniqueConstraints =
@UniqueConstraint(columnNames = "name", name = "restaurants_unique_name_idx"))
public class Restaurant extends AbstractNamedEntity {

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
