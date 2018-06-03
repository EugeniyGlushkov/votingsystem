package ru.alvisid.votingsystem.to;

import java.util.Map;
import java.util.Objects;

public class MenuToday {
    private final Integer menuId;

    private final Map<String, Float> price;

    public MenuToday(Integer menuId, Map<String, Float> price) {
        this.menuId = menuId;
        this.price = price;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public Map<String, Float> getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuToday menuToday = (MenuToday) o;
        return Objects.equals(menuId, menuToday.menuId) &&
                Objects.equals(price, menuToday.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(menuId, price);
    }

    @Override
    public String toString() {
        return "MenuToday (" +
                "menu id=" + menuId +
                " price=" + price +
                ')';
    }
}
