package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.tiem625.parkcleaner.domain.Point;
import com.tiem625.parkcleaner.domain.Size;

public class DrawRegionComponent implements Component {

    private Point origin;
    private Size size;

    public DrawRegionComponent() {
        this.origin = Point.ORIGIN;
        this.size = Size.COLLAPSED;
    }

    public DrawRegionComponent(Point origin, Size size) {
        this.origin = origin;
        this.size = size;
    }

    public Point origin() {
        return this.origin;
    }

    public Size size() {
        return this.size;
    }
}
