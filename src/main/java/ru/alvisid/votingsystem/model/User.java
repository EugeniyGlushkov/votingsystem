package ru.alvisid.votingsystem.model;

import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name")
})
@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "users_unique_name_idx"))
public class User extends AbstractNamedEntity {

    public static final String DELETE = "User.delete";
    public static final String ALL_SORTED = "User.getAllSorted";

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_idx"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set <Role> roles;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Vote> votes;

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

    public List<Vote> getVotes() {
        return votes;
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
