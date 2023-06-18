package com.tiem625.parkcleaner.basket;

import com.badlogic.ashley.core.Entity;
import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.components.VelocityComponent;
import com.tiem625.parkcleaner.domain.Position;

public class Basket extends Entity {

    public Basket(float posX, float posY) {
        this.add(new TextureComponent());
        this.add(new PlayerInputComponent());
        this.add(new VelocityComponent());
        this.add(new PositionComponent(Position.of(posX, posY)));
    }
}
