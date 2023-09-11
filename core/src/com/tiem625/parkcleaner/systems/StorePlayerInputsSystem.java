package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.domain.GameKey;
import com.tiem625.parkcleaner.ecs.ECS;

import java.util.Set;

public class StorePlayerInputsSystem extends IteratingSystem {

    private static final ComponentMapper<PlayerInputComponent> EntityPlayerControls = ECS.mapperFor(PlayerInputComponent.class);
    public static final Family PLAYER_CONTROLLED_FAMILY = Family.all(PlayerInputComponent.class).get();

    public StorePlayerInputsSystem(Family family) {
        super(PLAYER_CONTROLLED_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Set<GameKey> pressedKeys = scanPressedKeys();
        var input = EntityPlayerControls.get(entity);
        input.releaseAllKeys();
        pressedKeys.forEach(input::pressKey);
    }

    private Set<GameKey> scanPressedKeys() {

        return Set.of();
    }
}
