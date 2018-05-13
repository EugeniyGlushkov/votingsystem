package ru.alvisid.votingsystem.model;

public class Vote {
    private final User user;
    private Menu menu;

    public Vote(User user, Menu menu) {
        this.user = user;
        this.menu = menu;
    }

    public Vote(Vote vote) {
        this(vote.getUser(), vote.getMenu());
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
                "user=" + user +
                " menu=" + menu +
                ')';
    }
}
