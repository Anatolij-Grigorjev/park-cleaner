package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.domain.GameKey;
import org.junit.jupiter.api.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StorePlayerInputsSystemTests {

    Entity testEntity;
    StorePlayerInputsSystem system;
    
    @BeforeEach
    void setupEntity() {

        testEntity = new Entity();
        testEntity.add(new PlayerInputComponent());

        system = new StorePlayerInputsSystem();
    }

    @Test
    public void system_records_pressed_gamekeys() {
        var testActionGameKey = new GameKey("TestAction", Input.Keys.T);
        //TODO: set key pressed
        Assertions.assertFalse(testEntity.getComponent(PlayerInputComponent.class).isKeyPressed(testActionGameKey));
        system.processEntity(testEntity, Gdx.graphics.getDeltaTime());
        Assertions.assertTrue(testEntity.getComponent(PlayerInputComponent.class).isKeyPressed(testActionGameKey));
    }
}
