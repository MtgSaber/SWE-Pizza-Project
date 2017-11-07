package net.mtgsaber.projects.groupprojects.swe3313fall2017.data_handling;

import java.math.BigDecimal;

/**
 * Represents a transaction.
 * Author: Andrew Arnold (11/6/2017)
 */
public class Transaction {
    private final Order order;
    private final Integer[] paymentMethods;

    public Transaction(Order order, Integer[] paymentMethods) {
        this.order = order;
        this.paymentMethods = paymentMethods;
    }

    public Order getOrder() {
        return order;
    }

    public Integer[] getPaymentMethods() {
        return paymentMethods;
    }
}
