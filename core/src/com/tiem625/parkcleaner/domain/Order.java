package com.tiem625.parkcleaner.domain;

public record Order(int position) {
    public static final Order NEUTRAL = new Order(0);
}
