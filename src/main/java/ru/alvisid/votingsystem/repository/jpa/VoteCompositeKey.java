package ru.alvisid.votingsystem.repository.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VoteCompositeKey implements Serializable{

    //@Column(name = "user_id", nullable = false)
    private int user_id;

    //@Column(name = "menu_id", nullable = false)
    private int menu_id;

    public VoteCompositeKey() {
    }

     public VoteCompositeKey(int userId, int menuId) {
        this.user_id = userId;
        this.menu_id = menuId;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userId) {
        this.user_id = userId;
    }

    public int getMenuId() {
        return menu_id;
    }

    public void setMenuId(int menuId) {
        this.menu_id = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteCompositeKey that = (VoteCompositeKey) o;

        if (user_id != that.user_id) return false;
        return menu_id == that.menu_id;
    }

    @Override
    public int hashCode() {
        int result = user_id;
        result = 31 * result + menu_id;
        return result;
    }
}
