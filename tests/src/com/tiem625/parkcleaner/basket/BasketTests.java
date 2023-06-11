package com.tiem625.parkcleaner.basket;

import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.components.VelocityComponent;
import com.tiem625.parkcleaner.testsupport.ComponentAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Set;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BasketTests {

    private Basket basket;

    @BeforeEach
    void setup() {
        basket = new Basket();
    }

    @Test
    public void new_basket_has_components() {
        ComponentAssertions.assertEntityHasComponents(
                basket,
                Set.of(
                        TextureComponent.class,
                        PositionComponent.class,
                        PlayerInputComponent.class,
                        VelocityComponent.class
                )
        );
    }
}
