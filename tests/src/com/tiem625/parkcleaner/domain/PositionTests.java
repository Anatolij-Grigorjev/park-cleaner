package com.tiem625.parkcleaner.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PositionTests {

    @Test
    public void origin_from_position_same_coord_unary_scale_no_rotation() {
        float x = (float) Math.random();
        float y = (float) Math.random();
        var position = Position.of(x, y);
        var origin = position.asOrigin();
        Assertions.assertEquals(origin.position(), position);
        Assertions.assertEquals(origin.scale(), Scale.ORIGINAL);
        Assertions.assertEquals(origin.rotation(), Rotation.NONE);
    }
}
