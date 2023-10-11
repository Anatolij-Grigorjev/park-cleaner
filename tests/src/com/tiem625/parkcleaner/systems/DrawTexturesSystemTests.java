package com.tiem625.parkcleaner.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tiem625.parkcleaner.components.DrawRegionComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.domain.Size;
import com.tiem625.parkcleaner.testsupport.ECSSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.tiem625.parkcleaner.testsupport.GdxScaffolding.mockTexture;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DrawTexturesSystemTests {

    private final SpriteBatch spriteBatch = Mockito.mock(SpriteBatch.class);

    private DrawTexturesSystem system;

    @BeforeEach
    public void setup() {
        system = new DrawTexturesSystem(spriteBatch);
    }

    @Test
    public void draws_entity_texture_in_region() {
        var entity = ECSSupport.mockEntity(new TextureComponent(), new DrawRegionComponent(), new PositionComponent());
        TextureComponent textureComponent = entity.getComponent(TextureComponent.class);
        int texWidth = 56;
        int texHeight = 44;
        textureComponent.texture = mockTexture(Size.of(56, 44));
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
    public void does_not_draw_textures_without_regions() {
        var entityOnlyTexture = ECSSupport.mockEntity(new TextureComponent());
        var entityNoPosition = ECSSupport.mockEntity(new TextureComponent(), new DrawRegionComponent());
        var entityNoRegion = ECSSupport.mockEntity(new TextureComponent(), new PositionComponent());
        ECSSupport.setupEngineForEntities(system, entityOnlyTexture, entityNoRegion, entityNoPosition);

        system.update(1.0f);

        Mockito.verifyZeroInteractions(spriteBatch);
    }

    @Test
    public void draws_textures_sorted_by_proximity() {
        throw new UnsupportedOperationException("TODO");
    }
}
