package com.tiem625.parkcleaner.basket;

import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.components.VelocityComponent;
import com.tiem625.parkcleaner.domain.Position;
import com.tiem625.parkcleaner.testsupport.ComponentAssertions;
import org.junit.jupiter.api.*;

import java.util.Set;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BasketTests {

    private Basket basket;

    @BeforeEach
    void setup() {
        basket = new Basket(0f, 0f);
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

    @Test
    public void new_basket_has_set_position() {
        var positionComponent = basket.getComponent(PositionComponent.class);
        Assertions.assertEquals(positionComponent.data, Position.ZERO);
    }
}
