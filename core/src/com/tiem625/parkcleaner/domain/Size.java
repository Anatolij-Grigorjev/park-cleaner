package com.tiem625.parkcleaner.domain;

public record Size(float width, float height) {

    public static final Size COLLAPSED = new Size(0f, 0f);

    public static Size of(int width, int height) {
        return new Size(width, height);
    }
}
