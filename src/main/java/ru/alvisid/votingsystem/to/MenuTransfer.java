package ru.alvisid.votingsystem.to;

import ru.alvisid.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MenuTransfer {
    private final int id;
    private final int restaurantId;
    private final String restaurantName;
    private final LocalDate date;
    private final Map<String, Float> price;
    private final List<Vote> votes;

    public MenuTransfer(int id, int restaurantId, String restaurantName,LocalDate date, Map <String, Float> price, List <Vote> votes) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.date = date;
        this.price = price;
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map <String, Float> getPrice() {
        return price;
    }

    public List <Vote> getVotes() {
        return votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuTransfer that = (MenuTransfer) o;

        if (id != that.id) return false;
        if (restaurantId != that.restaurantId) return false;
        if (!restaurantName.equals(that.restaurantName)) return false;
        if (!date.equals(that.date)) return false;
        if (!price.equals(that.price)) return false;
        return votes.equals(that.votes);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + restaurantId;
        result = 31 * result + restaurantName.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + votes.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MenuTransfer{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", votes=" + votes +
                '}';
    }
}
