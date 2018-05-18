package ru.alvisid.votingsystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NamedQueries({
        @NamedQuery(name = V)
})
@Entity
@IdClass(Vote.VoteCompositeKey.class)
@Table(name = "votes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "menu_id"}, name = "votes_idx"))
public class Vote implements Serializable {

    public static final String DELETE = "Vote.delete";

    /*@Id
    @AttributeOverrides({
            @AttributeOverride(name = "user_id",
                    column = @Column(name="user_id")),
            @AttributeOverride(name = "menu_id",
                    column = @Column(name="menu_id"))
    })*/
    @Id
    private int userId;
    @Id
    private int menuId;

    /*@EmbeddedId
    VoteCompositeKey id;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false, updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Menu menu;

    public Vote() {
    }

    public Vote(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
        this.userId = user.getId();
        this.menuId = menu.getId();
    }

    public Vote(Vote vote) {
        this(vote.getUser(), vote.getMenu());
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        this.menuId = menu.getId();
    }

    public User getUser() {
        return user;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getUserId() {
        return userId;
    }

    /*public void setUserId(int userId) {
        this.userId = userId;
    }*/

    public int getMenuId() {
        return menuId;
    }

    /*public void setMenuId(int menuId) {
        this.menuId = menuId;
    }*/

    /*public VoteCompositeKey getId() {
        return id;
    }

    public void setId(VoteCompositeKey id) {
        this.id = id;
    }*/

    @Override
    public String toString() {
        return "Vote (" +
                "userId=" + userId +
                " menuId=" + menuId +
                ')';
    }

    @Embeddable
    public static class VoteCompositeKey implements Serializable {

        @Column(name = "user_id", nullable = false)
        private int userId;

        @Column(name = "menu_id", nullable = false)
        private int menuId;

        public VoteCompositeKey() {
        }

        public VoteCompositeKey(int userId, int menuId) {
            this.userId = userId;
            this.menuId = menuId;
        }

        private int getUserId() {
            return userId;
        }

        private void setUserId(int userId) {
            this.userId = userId;
        }

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            VoteCompositeKey that = (VoteCompositeKey) o;

            if (userId != that.userId) return false;
            return menuId == that.menuId;
        }

        @Override
        public int hashCode() {
            int result = userId;
            result = 31 * result + menuId;
            return result;
        }
    }
}
