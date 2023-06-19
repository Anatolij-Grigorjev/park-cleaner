package com.tiem625.parkcleaner.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.tiem625.parkcleaner.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ECS {

    private ECS() {
        throw new UnsupportedOperationException("global holder");
    }

    private static final Map<? extends Class<? extends Component>, ComponentMapper<?>> MAPPERS;
    static {
        Set<Class<? extends Component>> classes = Reflections
                .classNamesInPackage("com.tiem625.parkcleaner.components")
                .filter(fullClassName -> fullClassName.endsWith("Component"))
                .map(ECS::asComponentClass)
                .collect(Collectors.toSet());
        MAPPERS = classes.stream()
                .map(componentClass -> Map.entry(componentClass, ComponentMapper.getFor(componentClass)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<? extends Class<? extends Component>, ComponentMapper<?>> mappers() {
        return MAPPERS;
    }

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
}
