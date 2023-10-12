package com.tiem625.parkcleaner.testsupport;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.tiem625.parkcleaner.components.DrawRegionComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.ScreenOrderComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.ecsbase.ECS;

import java.util.Arrays;

public class ECSSupport {

    private ECSSupport() {
        throw new UnsupportedOperationException("static helper");
    }

    public static void setupEngineForEntities(EntitySystem system, Entity... entities) {
        Engine engine = ECS.engine();
        engine.addSystem(system);
        Arrays.stream(entities).forEach(engine::addEntity);
    }

    /**
     * Creates an entity with the provided <code>components</code>
     * @param components
     * @return the created entity with components added
     */
    public static Entity buildEntity(Component... components) {
        var entity = new Entity();
        Arrays.stream(components).forEach(entity::add);
        return entity;
    }

    /**
     * Creates a new entity that has components conforming to those required by the
     * {@link com.tiem625.parkcleaner.systems.DrawTexturesSystem#DRAWABLE_ENTITIES} family
     * @return a new entity with components required to be drawn
     */
    public static Entity buildDrawableEntity() {
        return buildEntity(
                new TextureComponent(),
                new DrawRegionComponent(),
                new PositionComponent(),
                new ScreenOrderComponent()
        );
    }
}
