package com.tiem625.parkcleaner.basket;

import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.components.VelocityComponent;
import com.tiem625.parkcleaner.domain.Position;
import com.tiem625.parkcleaner.ecs.EntitySupport;

public class Basket extends EntitySupport {

    public Basket(float posX, float posY) {
        this.add(new TextureComponent());
        this.add(new PlayerInputComponent());
        this.add(new VelocityComponent());
        this.add(new PositionComponent(Position.of(posX, posY)));
    }
}
