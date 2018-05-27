package ru.alvisid.votingsystem.to;

import java.time.LocalDate;
import java.util.Map;

public class PriceTransfer {
    private final String restaurantName;
    private final LocalDate date;
    private final Map<String, Float> price;

    public PriceTransfer(String restaurantName, LocalDate date, Map <String, Float> price) {
        this.restaurantName = restaurantName;
        this.date = date;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceTransfer that = (PriceTransfer) o;

        if (!restaurantName.equals(that.restaurantName)) return false;
        if (!date.equals(that.date)) return false;
        return price.equals(that.price);
    }

    @Override
    public int hashCode() {
        int result = restaurantName.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PriceTransfer{" +
                "restaurantName='" + restaurantName + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
