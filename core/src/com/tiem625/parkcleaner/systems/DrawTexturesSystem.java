package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tiem625.parkcleaner.components.DrawRegionComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.TextureComponent;

import static java.util.Objects.requireNonNull;

public class DrawTexturesSystem extends IteratingSystem {

    private static final Family DRAWABLE_ENTITIES = Family
            .all(TextureComponent.class, PositionComponent.class, DrawRegionComponent.class)
            .get();
    private final SpriteBatch spriteBatch;

    public DrawTexturesSystem(SpriteBatch spriteBatch) {
        super(DRAWABLE_ENTITIES);
        this.spriteBatch = requireNonNull(spriteBatch);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
