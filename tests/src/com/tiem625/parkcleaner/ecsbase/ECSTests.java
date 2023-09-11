package com.tiem625.parkcleaner.ecsbase;

import com.tiem625.parkcleaner.components.PositionComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ECSTests {

    @Test
    public void ecs_requested_mapper_gets_created() {
        Assertions.assertNotNull(ECS.mapperFor(PositionComponent.class));
    }
}
