package com.tiem625.parkcleaner.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    private static class ComponentMappersHolder {

        private Map<Class<? extends Component>, ComponentMapper<?>> data;

        private ComponentMappersHolder() {
            data = new ConcurrentHashMap<>();
        }

        private <C extends Component> ComponentMapper<C> mapperFor(Class<C> key) {
            return (ComponentMapper<C>) data.computeIfAbsent(key, ComponentMapper::getFor);
        }

        private void addMapperFor(Class<? extends Component> componentClazz) {
            data.put(componentClazz, ComponentMapper.getFor(componentClazz));
        }
    }
}
