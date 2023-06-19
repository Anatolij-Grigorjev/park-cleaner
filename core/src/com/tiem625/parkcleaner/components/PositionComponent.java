package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.tiem625.parkcleaner.domain.Position;

public class PositionComponent implements Component {

    private Position position;

    public PositionComponent() {
        this(Position.ZERO);
    }

    public PositionComponent(Position position) {
        this.position = position;
    }

    public Position position() {
        return position;
    }

    public void setPosition(Position nextPosition) {
        this.position = nextPosition;
    }

    public void adjustPositionBy(float x, float y) {
        this.position = position.translatedBy(x, y);
    }
}
