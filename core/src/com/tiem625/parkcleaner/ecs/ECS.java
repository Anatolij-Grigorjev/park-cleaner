package com.tiem625.parkcleaner.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.tiem625.parkcleaner.Reflections;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ECS {

    private ECS() {
        throw new UnsupportedOperationException("global holder");
    }

    private static final ComponentMappersMap MAPPERS;
    private static final Engine engine = new Engine();

    static {
        Set<Class<? extends Component>> classes = Reflections
                .classNamesInPackage("com.tiem625.parkcleaner.components")
                .filter(fullClassName -> fullClassName.endsWith("Component"))
                .map(ECS::asComponentClass)
                .collect(Collectors.toSet());
        MAPPERS = new ComponentMappersMap();
        classes.forEach(componentClazz -> MAPPERS.put(componentClazz, ComponentMapper.getFor(componentClazz)));
    }

    public static ComponentMappersMap mappers() {
        return MAPPERS;
    }
    public static Engine engine() { return engine; }

    private static Class<? extends Component> asComponentClass(String fullClassName) {
        try {
            var foundClass = Class.forName(fullClassName);
            if (foundClass.isAssignableFrom(Component.class)) {
                return (Class<? extends Component>) foundClass;
            } else {
                throw new RuntimeException(fullClassName + " is not a component class! Its " + foundClass);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static class ComponentMappersMap extends ConcurrentHashMap<Class<? extends Component>, ComponentMapper<?>> {

        public <C extends Component> ComponentMapper<C> get(Class<C> key) {
            return (ComponentMapper<C>) super.get(key);
        }
    }
}
