package ru.alvisid.votingsystem.model;

import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractNamedEntity {


    private Set<Role> roles;

    public User(Integer id, String name, Set<Role> roles) {
        super(id, name);
        this.roles = roles;
    }

    public User(Integer id, String name, Role role, Role... roles) {
        this(id, name, EnumSet.of(role, roles));
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User (" +
                "id=" + id +
                " name=" + name +
                " roles=" + roles +
                ')';
    }
}
