package com.tiem625.parkcleaner.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tiem625.parkcleaner.components.DrawRegionComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.ScreenOrderComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.domain.Size;
import com.tiem625.parkcleaner.testsupport.ECSSupport;
import com.tiem625.parkcleaner.testsupport.GdxScaffolding;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DrawTexturesSystemTests {

    private final SpriteBatch spriteBatch = Mockito.mock(SpriteBatch.class);

    private DrawTexturesSystem system;

    @BeforeEach
    public void setup() {
        system = new DrawTexturesSystem(spriteBatch);
    }

    @AfterEach
    public void clear() {
        ECSSupport.clearEngine();
    }

    @Test
    public void draws_entity_texture_in_region() {
        var entity = ECSSupport.buildDrawableEntity();
        TextureComponent textureComponent = entity.getComponent(TextureComponent.class);
        int texWidth = 56;
        int texHeight = 44;
        textureComponent.texture = GdxScaffolding.mockTexture(Size.of(56, 44));
        DrawRegionComponent drawRegionComponent = entity.getComponent(DrawRegionComponent.class);
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);
        ECSSupport.setupEngineForEntities(system, entity);

        system.update(1.0f);

        Mockito.verify(spriteBatch).draw(
                Mockito.eq(textureComponent.texture),
                Mockito.eq(positionComponent.position().x()),
                Mockito.eq(positionComponent.position().y()),
                Mockito.eq(drawRegionComponent.origin().position().x()),
                Mockito.eq(drawRegionComponent.origin().position().y()),
                Mockito.eq(drawRegionComponent.size().width()),
                Mockito.eq(drawRegionComponent.size().height()),
                Mockito.eq(drawRegionComponent.origin().scale().horizontal()),
                Mockito.eq(drawRegionComponent.origin().scale().vertical()),
                Mockito.eq(drawRegionComponent.origin().rotation().directed()),
                Mockito.eq(0), //srcX
                Mockito.eq(0), //srcY
                Mockito.eq(texWidth),
                Mockito.eq(texHeight),
                Mockito.eq(false), //flipX
                Mockito.eq(false) //flipY
        );
    }

    @Test
    public void does_not_draw_textures_without_details() {
        var entityOnlyTexture = ECSSupport.buildEntity(new TextureComponent());
        var entityNoPosition = ECSSupport.buildEntity(new TextureComponent(), new DrawRegionComponent());
        var entityNoRegion = ECSSupport.buildEntity(new TextureComponent(), new PositionComponent());
        var entityNoOrder = ECSSupport.buildEntity(new TextureComponent(), new PositionComponent(), new DrawRegionComponent());
        ECSSupport.setupEngineForEntities(system, entityOnlyTexture, entityNoRegion, entityNoPosition, entityNoOrder);

        system.update(1.0f);

        Mockito.verifyZeroInteractions(spriteBatch);
    }

    @Test
    public void draws_textures_sorted_by_proximity() {
        var entity1 = ECSSupport.buildDrawableEntity();
        var entity2 = ECSSupport.buildDrawableEntity();
        entity1.getComponent(ScreenOrderComponent.class).moveBack();
        entity2.getComponent(ScreenOrderComponent.class).moveCloser();
        var entity1Tex = GdxScaffolding.mockTexture(Size.COLLAPSED);
        var entity2Tex = GdxScaffolding.mockTexture(Size.COLLAPSED);
        entity1.getComponent(TextureComponent.class).texture = entity1Tex;
        entity2.getComponent(TextureComponent.class).texture = entity2Tex;
        ECSSupport.setupEngineForEntities(system, entity1, entity2);

        system.update(1.0f);

        //draw entity 1
        Mockito.verify(spriteBatch).draw(
                Mockito.eq(entity1Tex),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),

                Mockito.anyInt(),
                Mockito.anyInt(),
                Mockito.anyInt(),
                Mockito.anyInt(),

                Mockito.anyBoolean(),
                Mockito.anyBoolean()
        );
        //draw entity 2
        Mockito.verify(spriteBatch).draw(
                Mockito.eq(entity2Tex),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),
                Mockito.anyFloat(),

                Mockito.anyInt(),
                Mockito.anyInt(),
                Mockito.anyInt(),
                Mockito.anyInt(),

                Mockito.anyBoolean(),
                Mockito.anyBoolean()
        );
    }
}
