package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.VelocityComponent;
import com.tiem625.parkcleaner.ecs.ECS;

public class MovePositionsSystem extends IteratingSystem {

    private static final Family MOVING_POSITIONS_FAMILY = Family.all(VelocityComponent.class, PositionComponent.class).get();
    private static final ComponentMapper<PositionComponent> EntityPosition = ECS.mapperFor(PositionComponent.class);
    private static final ComponentMapper<VelocityComponent> EntityVelocity = ECS.mapperFor(VelocityComponent.class);

    public MovePositionsSystem() {
        super(MOVING_POSITIONS_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        var positionChange = EntityVelocity.get(entity).velocity().scaledBy(deltaTime).position();
        EntityPosition.get(entity).adjustPositionBy(positionChange);
    }
}
