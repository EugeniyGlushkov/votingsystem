package ru.alvisid.votingsystem.model;

public class Restaurant extends AbstractNamedEntity {
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
