package ru.alvisid.votingsystem.to;

import java.time.LocalDate;

public class RestaurantVotes {
    private final Integer voteId;

    private final Integer restaurantId;

    private final String restaurantName;

    private final LocalDate date;

    private final int totalVotesForRestaurantADay;

    private final int totalVotesForADay;

    public RestaurantVotes(Integer voteId, Integer restaurantId, String restaurantName, LocalDate date, int totalVotesForRestaurantADay, int totalVotesForADay) {
        this.voteId = voteId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.date = date;
        this.totalVotesForRestaurantADay = totalVotesForRestaurantADay;
        this.totalVotesForADay = totalVotesForADay;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalVotesForRestaurantADay() {
        return totalVotesForRestaurantADay;
    }

    public int getTotalVotesForADay() {
        return totalVotesForADay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantVotes that = (RestaurantVotes) o;

        if (voteId != that.voteId || restaurantId != that.restaurantId) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = voteId;
        result = 31 * result + restaurantId;
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RestaurantVotes (" +
                "vote id=" + voteId +
                " restaurant id=" + restaurantId +
                " Restaurant=" + restaurantName +
                " date=" + date +
                " totalVotesForRestaurantADay=" + totalVotesForRestaurantADay +
                " totalVotesForADay=" + totalVotesForADay +
                ')';
    }
}
