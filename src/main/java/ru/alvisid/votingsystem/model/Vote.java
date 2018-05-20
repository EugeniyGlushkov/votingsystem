package ru.alvisid.votingsystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.USER_ZERO_DELETE, query = "UPDATE Vote v SET v.user=:zeroId WHERE v.id=:id"),
        @NamedQuery(name = Vote.MENU_ZERO_DELETE, query = "UPDATE Vote v SET v.menu=:zeroId WHERE v.id=:id")
})
@Entity
@Table(name = "votes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "menu_id"}, name = "votes_idx"))
public class Vote extends AbsractBaseEntity {

    public static final String DELETE = "Vote.delete";
    public static final String USER_ZERO_DELETE = "Vote.userZeroDelete";
    public static final String MENU_ZERO_DELETE = "Vote.menuZeroDelete";
    public static final String ALL_SORTED = "Vote.getAllSorted";
    public static final String ALL_BY_USER_ID = "Vote.getAllByUserId";
    public static final String ALL_BY_MENU_ID = "Vote.getAllByMenuId";

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
    }

    public Vote(int id, User user, Menu menu) {
        super(id);
        this.user = user;
        this.menu = menu;
    }

    public Vote(Vote vote) {
        this(vote.getUser(), vote.getMenu());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public User getUser() {
        return user;
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "Vote (" +
                "userId=" + user.getId() +
                " menuId=" + menu.getId() +
                ')';
    }
}

/*@Entity
//@IdClass(Vote.VoteCompositeKey.class)
@Table(name = "votes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "menu_id"}, name = "votes_idx"))
public class Vote implements Serializable {

    public static final String DELETE = "Vote.delete";

    *//*@Id
    @AttributeOverrides({
            @AttributeOverride(name = "user_id",
                    column = @Column(name="user_id")),
            @AttributeOverride(name = "menu_id",
                    column = @Column(name="menu_id"))
    })*//*
    *//*@Id
    private int userId;
    @Id
    private int menuId;*//*

    @EmbeddedId
    VoteCompositeKey id;

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

    public int getUserId(){
        return id.getUserId();
    }

    public int getMenuId(){
        return id.getMenuId();
    }

    public Vote() {
    }

    public Vote(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
        this.id = new VoteCompositeKey(user.getId(), menu.getId());
    }

    public Vote(Vote vote) {
        this(vote.getUser(), vote.getMenu());
    }

    public void setUser(User user) {
        this.user = user;
        id.setUserId(user.getId());
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
        id.setMenuId(menu.getId());
    }

    public User getUser() {
        return user;
    }

    public Menu getMenu() {
        return menu;
    }

    *//*public int getUserId() {
        return userId;
    }*//*

    *//*public void setUserId(int userId) {
        this.userId = userId;
    }*//*

    *//*public int getMenuId() {
        return menuId;
    }*//*

    *//*public void setMenuId(int menuId) {
        this.menuId = menuId;
    }*//*

    public VoteCompositeKey getId() {
        return id;
    }

    public void setId(VoteCompositeKey id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Vote (" +
                "userId=" + id.getUserId() +
                " menuId=" + id.getMenuId() +
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
}*/

/*
@Entity
@IdClass(Vote.VoteCompositeKey.class)
@Table(name = "votes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "menu_id"}, name = "votes_idx"))
public class Vote implements Serializable {

    public static final String DELETE = "Vote.delete";

    */
/*@Id
    @AttributeOverrides({
            @AttributeOverride(name = "user_id",
                    column = @Column(name="user_id")),
            @AttributeOverride(name = "menu_id",
                    column = @Column(name="menu_id"))
    })*//*

    @Id
    private int userId;
    @Id
    private int menuId;

    */
/*@EmbeddedId
    VoteCompositeKey id;*//*


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

    */
/*public void setUserId(int userId) {
        this.userId = userId;
    }*//*


    public int getMenuId() {
        return menuId;
    }

    */
/*public void setMenuId(int menuId) {
        this.menuId = menuId;
    }*//*


    */
/*public VoteCompositeKey getId() {
        return id;
    }

    public void setId(VoteCompositeKey id) {
        this.id = id;
    }*//*


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
}*/
