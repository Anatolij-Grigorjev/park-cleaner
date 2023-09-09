package com.tiem625.parkcleaner.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;

public class ECS {

    private ECS() {
        throw new UnsupportedOperationException("global holder");
    }

    private static final ComponentMappersHolder MAPPERS = new ComponentMappersHolder();
    private static final Engine engine = new Engine();

    public static Engine engine() {
        return engine;
    }

    public static <C extends Component> ComponentMapper<C> mapperFor(Class<C> componentClazz) {
        return MAPPERS.mapperFor(componentClazz);
    }
}
