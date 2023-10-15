package com.tiem625.parkcleaner.domain;

public record Order(int position) implements Comparable<Order> {
    public static final Order NEUTRAL = new Order(0);

    @Override
    public int compareTo(Order o) {
        return position() - o.position();
    }
}
