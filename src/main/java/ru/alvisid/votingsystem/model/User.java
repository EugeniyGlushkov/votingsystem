package ru.alvisid.votingsystem.model;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "users_unique_name_idx"))
public class User extends AbstractNamedEntity {

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_idx"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set <Role> roles;

    public User(){
        super();
    }

    public User(String name, Set <Role> roles) {
        super(name);
        this.roles = roles;
    }

    public User(String name, Role role, Role... roles) {
        this(name, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, Set <Role> roles) {
        super(id, name);
        this.roles = roles;
    }

    public User(Integer id, String name, Role role, Role... roles) {
        this(id, name, EnumSet.of(role, roles));
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getRoles());
    }

    public Set <Role> getRoles() {
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
