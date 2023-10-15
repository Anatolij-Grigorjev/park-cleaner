package com.tiem625.parkcleaner.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tiem625.parkcleaner.components.DrawRegionComponent;
import com.tiem625.parkcleaner.components.PositionComponent;
import com.tiem625.parkcleaner.components.ScreenOrderComponent;
import com.tiem625.parkcleaner.components.TextureComponent;
import com.tiem625.parkcleaner.ecsbase.ECS;

import java.util.Comparator;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class DrawTexturesSystem extends SortedIteratingSystem {

    public static final Set<Class<? extends Component>> RENDER_ENTITY_COMPONENTS = Set.of(
            TextureComponent.class,
            PositionComponent.class,
            DrawRegionComponent.class,
            ScreenOrderComponent.class
    );
    private static final Family DRAWABLE_ENTITIES = Family
            .all(RENDER_ENTITY_COMPONENTS.toArray(new Class[0]))
            .get();
    private final SpriteBatch spriteBatch;
    private final EntityRenderer entityRenderer;

    public DrawTexturesSystem(SpriteBatch spriteBatch) {
        super(DRAWABLE_ENTITIES, new ScreenOrderComparator());
        this.spriteBatch = requireNonNull(spriteBatch);
        this.entityRenderer = new EntityRenderer(spriteBatch);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityRenderer.renderEntity(entity);
    }

    private static class EntityRenderer {

        private final SpriteBatch spriteBatch;
        private final ComponentMapper<TextureComponent> TextureMapper = ECS.mapperFor(TextureComponent.class);
        private final ComponentMapper<PositionComponent> PositionMapper = ECS.mapperFor(PositionComponent.class);
        private final ComponentMapper<DrawRegionComponent> DrawRegionMapper = ECS.mapperFor(DrawRegionComponent.class);

        private EntityRenderer(SpriteBatch spriteBatch) {
            this.spriteBatch = spriteBatch;
        }

        public void renderEntity(Entity entity) {
            var texture = TextureMapper.get(entity).texture;
            var position = PositionMapper.get(entity).position();
            var drawRegion = DrawRegionMapper.get(entity);
            var drawRegionOrigin = drawRegion.origin();
            var drawRegionSize = drawRegion.size();

            spriteBatch.draw(
                texture,
                    position.x(),
                    position.y(),
                    drawRegionOrigin.position().x(),
                    drawRegionOrigin.position().y(),
                    drawRegionSize.width(),
                    drawRegionSize.height(),
                    drawRegionOrigin.scale().horizontal(),
                    drawRegionOrigin.scale().vertical(),
                    drawRegionOrigin.rotation().directed(),
                    0, 0,
                    texture.getWidth(),
                    texture.getHeight(),
                    false,
                    false
            );
        }
    }

    private static class ScreenOrderComparator implements Comparator<Entity> {

        private final ComponentMapper<ScreenOrderComponent> ScreenOrderMapper = ECS.mapperFor(ScreenOrderComponent.class);

        @Override
        public int compare(Entity o1, Entity o2) {
            var entity1Order = ScreenOrderMapper.get(o1).order();
            var entity2Order = ScreenOrderMapper.get(o2).order();
            return entity1Order.compareTo(entity2Order);
        }
    }
}
