package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author: Andrew Arnold (10/23/2017)
 */
public class Customer {
    private String name;
    private ArrayList<Order> orders;
    private String address;
    private long id;

    public Customer(String name, String address, long id, Order[] orders) {
        this.name = name;
        this.orders = new ArrayList<>(Arrays.asList(orders));
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addOrder(Order order) { orders.add(order); }
    public Order[] getOrders() {
        return orders.toArray(new Order[orders.size()]);
    }

    public long getId() { return id; }
}
