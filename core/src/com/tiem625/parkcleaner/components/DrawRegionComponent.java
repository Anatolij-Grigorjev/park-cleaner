package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.tiem625.parkcleaner.domain.Origin;
import com.tiem625.parkcleaner.domain.Size;

public class DrawRegionComponent implements Component {

    private Origin origin;
    private Size size;

    public DrawRegionComponent() {
        this.origin = Origin.DEFAULT;
        this.size = Size.COLLAPSED;
    }

    public DrawRegionComponent(Origin origin, Size size) {
        this.origin = origin;
        this.size = size;
    }

    public Origin origin() {
        return this.origin;
    }

    public Size size() {
        return this.size;
    }
}
