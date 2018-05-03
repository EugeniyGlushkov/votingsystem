package ru.alvisid.votingsystem.TO;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.User;

public class VoteWithSumVotes {
    private final User user;

    private final Menu menu;

    private final int totalVotesForRestaurantADay;

    private final int totalVotesForADay;

    public VoteWithSumVotes(User user, Menu menu, int totalVotesForRestaurantADay, int totalVotesForADay) {
        this.user = user;
        this.menu = menu;
        this.totalVotesForRestaurantADay = totalVotesForRestaurantADay;
        this.totalVotesForADay = totalVotesForADay;
    }

    public User getUser() {
        return user;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getTotalVotesForRestaurantADay() {
        return totalVotesForRestaurantADay;
    }

    public int getTotalVotesForADay() {
        return totalVotesForADay;
    }

    @Override
    public String toString() {
        return "VoteWithSumVotes (" +
                "user=" + user +
                " menu=" + menu +
                " totalVotesForRestaurantADay=" + totalVotesForRestaurantADay +
                " totalVotesForADay=" + totalVotesForADay +
                ')';
    }
}
