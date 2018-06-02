package ru.alvisid.votingsystem.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name")
})
@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "users_unique_name_idx"))
public class User extends AbstractNamedEntity {

    public static final String DELETE = "User.delete";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_idx"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set <Role> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @NotNull
    private Set<Vote> votes;

    public User(){
        super();
    }

    public User(String name, Set <Role> roles) {
        this(null, name, roles);
    }

    public User(String name, Role role, Role... roles) {
        this(name, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, Set <Role> roles) {
        this(id, name, roles, new HashSet <Vote>());
    }

    public User(Integer id, String name, Set <Role> roles, Set<Vote> votes) {
        super(id, name);
        this.roles = roles;
        this.votes = votes;
    }

    public User(Integer id, String name, Role role, Role... roles) {
        this(id, name, EnumSet.of(role, roles));
    }



    public User(User user) {
        this(user.getId(), user.getName(), user.getRoles(), user.getVotes());

    }

    public Set <Role> getRoles() {
        return roles;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
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
