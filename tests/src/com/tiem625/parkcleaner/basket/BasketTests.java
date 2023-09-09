package com.tiem625.parkcleaner.basket;

import com.badlogic.gdx.math.Vector2;
import com.tiem625.parkcleaner.components.PlayerInputComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.components.VelocityComponent;
import com.tiem625.parkcleaner.domain.Position;
import com.tiem625.parkcleaner.ecs.ECS;
import com.tiem625.parkcleaner.systems.MovePositionsSystem;
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
        Assertions.assertEquals(positionComponent.position(), Position.ZERO);
    }

    @Test
    public void new_basket_not_in_engine() {
        Assertions.assertFalse(ECS.engine().getEntities().contains(basket, true));
    }

    @Test
    public void basket_with_velocity_moved_by_engine() {
        basket.getComponent(VelocityComponent.class).addImpulse(Vector2.X);
        basket.process();
        ECS.engine().addSystem(new MovePositionsSystem());
        ECS.engine().update(1.0f);
        Assertions.assertEquals(1.0f, basket.getComponent(PositionComponent.class).position().x());
    }
}
