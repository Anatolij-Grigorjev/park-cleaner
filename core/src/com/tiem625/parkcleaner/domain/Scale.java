package com.tiem625.parkcleaner.domain;

public record Scale(float horizontal, float vertical) {

    public static final Scale ORIGINAL = new Scale(1, 1);
}
