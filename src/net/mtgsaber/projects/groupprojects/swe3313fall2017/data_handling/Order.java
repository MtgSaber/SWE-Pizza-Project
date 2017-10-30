package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: Andrew Arnold (10/23/2017)
 */
public class Order {
    private final Customer customer;
    private final User source;
    private ArrayList<Item> items;

    public Order(Customer customer, User source, Item[] items) {
        this.customer = customer;
        this.source = source;
        this.items = new ArrayList<>(Arrays.asList(items));
    }

    public Item[] getItems() { return items.toArray(new Item[items.size()]); }

    public void removeItem(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index > items.size()-1)
            throw new ArrayIndexOutOfBoundsException(index);
        else
            items.remove(index);
    }

    public Customer getCustomer() {
        return customer;
    }

    public User getSource() {
        return source;
    }

    public BigDecimal getPreTaxTotal() {
        BigDecimal total = new BigDecimal(0);
        for (Item item : items)
            total = total.add(item.getPrice());
        return total;
    }
}
