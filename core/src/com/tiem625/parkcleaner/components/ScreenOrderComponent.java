package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.tiem625.parkcleaner.domain.Order;

public class ScreenOrderComponent implements Component {

    private Order order;

    public ScreenOrderComponent() {
        this.order = Order.NEUTRAL;
    }

    public ScreenOrderComponent(Order order) {
        this.order = order;
    }

    public Order order() {
        return order;
    }

    public void setOrder(int position) {
        this.order = new Order(position);
    }

    public void moveBack() {
        this.order = new Order(order.position() - 1);
    }

    public void moveCloser() {
        this.order = new Order(order.position() + 1);
    }
}
