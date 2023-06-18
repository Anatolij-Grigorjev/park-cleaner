package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.tiem625.parkcleaner.domain.Position;

public class PositionComponent implements Component {

    public Position data;

    public PositionComponent() {
        this(Position.ZERO);
    }

    public PositionComponent(Position data) {
        this.data = data;
    }
}
