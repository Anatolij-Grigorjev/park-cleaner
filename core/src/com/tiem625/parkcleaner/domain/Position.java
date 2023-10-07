package com.tiem625.parkcleaner.domain;

public record Position(float x, float y) {

    public static final Position ZERO = of(0f, 0f);

    public static Position of(float x, float y) {
        return new Position(x, y);
    }

    public Position translatedBy(float x, float y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position same() {
        return of(x, y);
    }

    public Origin asOrigin() {
        return new Origin(same(), Scale.ORIGINAL, Rotation.NONE);
    }
}
