package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.Entity;
import com.tiem625.parkcleaner.components.PlayerInputComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

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

    }
}
