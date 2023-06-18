package com.tiem625.parkcleaner.domain;

public record Position(float x, float y) {

    public static final Position ZERO = of(0f, 0f);

    public static Position of(float x, float y) {
        return new Position(x, y);
    }
}
