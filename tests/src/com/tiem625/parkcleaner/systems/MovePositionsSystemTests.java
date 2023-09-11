package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.VelocityComponent;
import com.tiem625.parkcleaner.domain.Position;
import com.tiem625.parkcleaner.domain.Velocity;
import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MovePositionsSystemTests {

    Entity testEntity;
    MovePositionsSystem system;

    @BeforeEach
    void setupEntity() {
        testEntity = new Entity();
        testEntity.add(new VelocityComponent());
        testEntity.add(new PositionComponent());

        system = new MovePositionsSystem();
    }

    @Test
    public void system_changes_entity_position_according_velocity() {
        PositionComponent positionComponent = testEntity.getComponent(PositionComponent.class);
        positionComponent.setPosition(Position.ZERO);
        testEntity.getComponent(VelocityComponent.class).setVelocity(Velocity.of(Vector2.X));
        system.processEntity(testEntity, 1.0f);
        Assertions.assertEquals(positionComponent.position(), Position.of(1f, 0f));
    }
}