package ru.alvisid.votingsystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.alvisid.votingsystem.repository.jpa.VoteCompositeKey;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@IdClass(VoteCompositeKey.class)
@Table(name = "votes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "menu_id"}, name = "votes_idx"))
public class Vote implements Serializable{

    /*@Id
    @AttributeOverrides({
            @AttributeOverride(name = "user_id",
                    column = @Column(name="user_id")),
            @AttributeOverride(name = "menu_id",
                    column = @Column(name="menu_id"))
    })*/
    @Id
    private int user_id;
    @Id
    private int menu_id;

    /*@EmbeddedId
    VoteCompositeKey id;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false, updatable = false, insertable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    //@NotNull
    private Menu menu;

    public Vote() {
    }

    public Vote(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
        this.user_id = user.getId();
        this.menu_id = menu.getId();
    }

    public Vote(Vote vote) {
        this(vote.getUser(), vote.getMenu());
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        this.menu_id = menu.getId();
    }

    public User getUser() {
        return user;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    /*public VoteCompositeKey getId() {
        return id;
    }

    public void setId(VoteCompositeKey id) {
        this.id = id;
    }*/

    @Override
    public String toString() {
        return "Vote (" +
                "user=" + user +
                " menu=" + menu +
                ')';
    }

}
