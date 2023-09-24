package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private final GameKey testActionGameKey = new GameKey("TestAction", Input.Keys.T);

    @BeforeEach
    void setupEntity() {
        GdxInputHeadlessMock input = (GdxInputHeadlessMock) Gdx.input;
        input.setKeyPressed(testActionGameKey.keyBinding());
        testEntity = new Entity();
        testEntity.add(new PlayerInputComponent());

        system = new StorePlayerInputsSystem();
    }

    @Test
    public void system_records_pressed_gamekeys() {
        Assertions.assertFalse(testEntity.getComponent(PlayerInputComponent.class).isKeyPressed(testActionGameKey));
        system.processEntity(testEntity, Gdx.graphics.getDeltaTime());
        Assertions.assertTrue(testEntity.getComponent(PlayerInputComponent.class).isKeyPressed(testActionGameKey));
    }
}
