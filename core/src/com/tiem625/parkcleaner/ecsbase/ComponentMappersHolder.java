package com.tiem625.parkcleaner.ecsbase;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class ComponentMappersHolder {

    private Map<Class<? extends Component>, ComponentMapper<?>> data;

    ComponentMappersHolder() {
        data = new ConcurrentHashMap<>();
    }

    <C extends Component> ComponentMapper<C> mapperFor(Class<C> key) {
        return (ComponentMapper<C>) data.computeIfAbsent(key, ComponentMapper::getFor);
    }
}
