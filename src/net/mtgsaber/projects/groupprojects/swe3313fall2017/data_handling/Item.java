package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling;

import java.math.BigDecimal;

/**
 * Represents an Item from the Menu.
 * Author: Andrew Arnold (10/23/2017)
 */
public class Item<T extends ItemCategory> {
    private final String name;
    private final BigDecimal price;
    private final T category;

    public Item(String name, T category, double price) {
        this.name = name;
        this.price = BigDecimal.valueOf(price);
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public T getCategory() {
        return category;
    }
}
