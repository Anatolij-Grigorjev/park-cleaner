package com.tiem625.parkcleaner.domain;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

public class Velocity {

    public static final Velocity INERT = of(Vector2.Zero);

    private final Vector2 vector;

    public static Velocity of(Vector2 vector) {
        if (vector == null) {
            throw new IllegalArgumentException("velocity cant be null!");
        }
        return new Velocity(vector);
    }

    private Velocity(Vector2 vector) {
        this.vector = vector;
    }

    public float horizontal() {
        return vector.x;
    }

    public float vertical() {
        return vector.y;
    }

    public Vector2 vector() {
        return vector.cpy();
    }

    public Position position() {
        return Position.of(vector.x, vector.y);
    }

    public Velocity withImpulse(Vector2 impulse) {
        return Velocity.of(vector().add(impulse));
    }

    public Velocity scaledBy(float scale) {
        return Velocity.of(vector().scl(scale));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity velocity = (Velocity) o;
        return Objects.equals(vector, velocity.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector);
    }
}
