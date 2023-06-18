package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.tiem625.parkcleaner.domain.Velocity;

public class VelocityComponent implements Component {

    public Velocity velocity;

    public VelocityComponent() {
        this(Velocity.INERT);
    }

    public VelocityComponent(Velocity velocity) {
        this.velocity = velocity;
    }
}
