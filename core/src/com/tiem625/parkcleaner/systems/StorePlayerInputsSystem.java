package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.tiem625.parkcleaner.Inputs;
import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.domain.GameKey;
import com.tiem625.parkcleaner.ecsbase.ECS;

import java.util.Set;
import java.util.stream.Collectors;

public class StorePlayerInputsSystem extends IteratingSystem {

    private static final ComponentMapper<PlayerInputComponent> EntityPlayerControls = ECS.mapperFor(PlayerInputComponent.class);
    public static final Family PLAYER_CONTROLLED_FAMILY = Family.all(PlayerInputComponent.class).get();

    public StorePlayerInputsSystem() {
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
        return Inputs.current().stream()
                .filter(gameKey -> Gdx.input.isKeyPressed(gameKey.keyBinding()))
                .collect(Collectors.toSet());
    }
}
