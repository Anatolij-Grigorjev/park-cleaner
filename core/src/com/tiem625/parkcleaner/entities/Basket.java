package com.tiem625.parkcleaner.entities;

import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.components.VelocityComponent;
import com.tiem625.parkcleaner.domain.Position;
import com.tiem625.parkcleaner.ecsbase.EntitySupport;

public class Basket extends EntitySupport {

    public Basket(float posX, float posY) {
        this.addAll(
                new TextureComponent(),
                new PlayerInputComponent(),
                new VelocityComponent(),
                new PositionComponent(Position.of(posX, posY))
        );
    }
}
