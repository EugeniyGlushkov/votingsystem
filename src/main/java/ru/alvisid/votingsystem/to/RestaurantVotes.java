package ru.alvisid.votingsystem.to;

import java.time.LocalDate;

public class RestaurantVotes {
    private final Integer menuId;

    private final Integer restaurantId;

    private final String restaurantName;

    private final LocalDate date;

    private final int totalVotesForRestaurantADay;

    private final int totalVotesForADay;

    public RestaurantVotes(Integer menuId, Integer restaurantId, String restaurantName, LocalDate date, int totalVotesForRestaurantADay, int totalVotesForADay) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.date = date;
        this.totalVotesForRestaurantADay = totalVotesForRestaurantADay;
        this.totalVotesForADay = totalVotesForADay;
    }

    public Integer getmenuId() {
        return menuId;
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

        if (menuId != that.menuId || restaurantId != that.restaurantId) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result = menuId;
        result = 31 * result + restaurantId;
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RestaurantVotes (" +
                "menu id=" + menuId +
                " restaurant id=" + restaurantId +
                " Restaurant=" + restaurantName +
                " date=" + date +
                " totalVotesForRestaurantADay=" + totalVotesForRestaurantADay +
                " totalVotesForADay=" + totalVotesForADay +
                ')';
    }
}
