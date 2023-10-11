package com.tiem625.parkcleaner.testsupport;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
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

    public static Entity mockEntity(Component... components) {
        var entity = new Entity();
        Arrays.stream(components).forEach(entity::add);
        return entity;
    }
}
