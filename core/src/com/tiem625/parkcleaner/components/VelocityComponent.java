package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.tiem625.parkcleaner.domain.Velocity;

public class VelocityComponent implements Component {

    private Velocity velocity;

    public VelocityComponent() {
        this(Velocity.INERT);
    }

    public VelocityComponent(Velocity velocity) {
        this.velocity = velocity;
    }

    public Velocity velocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void addImpulse(Vector2 impulse) {
        this.velocity = velocity.withImpulse(impulse);
    }
}
