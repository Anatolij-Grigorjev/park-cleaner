package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.domain.GameKey;
import com.tiem625.parkcleaner.testsupport.GdxHeadlessSupport;
import com.tiem625.parkcleaner.testsupport.GdxInputHeadlessMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(GdxHeadlessSupport.class)
public class StorePlayerInputsSystemTests {

    Entity testEntity;
    StorePlayerInputsSystem system;
    private final GdxInputHeadlessMock input = (GdxInputHeadlessMock) Gdx.input;

    @BeforeEach
    void setupEntity() {
        testEntity = new Entity();
        testEntity.add(new PlayerInputComponent());

        system = new StorePlayerInputsSystem();
    }

    @Test
    public void system_records_pressed_gamekeys() {
        input.setKeyPressed(GameKey.TEST_ACTION.keyBinding());
        Assertions.assertFalse(testEntity.getComponent(PlayerInputComponent.class).isKeyPressed(GameKey.TEST_ACTION));
        system.processEntity(testEntity, Gdx.graphics.getDeltaTime());
        Assertions.assertTrue(testEntity.getComponent(PlayerInputComponent.class).isKeyPressed(GameKey.TEST_ACTION));
    }

    @Test
    public void system_clears_keys_no_longer_pressed() {
        input.setKeyPressed(GameKey.TEST_ACTION.keyBinding());
        system.processEntity(testEntity, Gdx.graphics.getDeltaTime());
        Assertions.assertTrue(testEntity.getComponent(PlayerInputComponent.class).isKeyPressed(GameKey.TEST_ACTION));
        input.clearKeyPressed(GameKey.TEST_ACTION.keyBinding());
        system.processEntity(testEntity, Gdx.graphics.getDeltaTime());
        Assertions.assertFalse(testEntity.getComponent(PlayerInputComponent.class).isKeyPressed(GameKey.TEST_ACTION));
    }
}
