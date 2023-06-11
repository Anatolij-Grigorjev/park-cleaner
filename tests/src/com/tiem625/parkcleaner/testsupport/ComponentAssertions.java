package com.tiem625.parkcleaner.testsupport;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComponentAssertions {

    private ComponentAssertions(){
        throw new UnsupportedOperationException("static helper");
    }

    public static void assertEntityHasComponents(Entity entity, Set<Class<? extends Component>> desiredComponentClasses) {
        assertNotNull(entity);
        assertNotNull(desiredComponentClasses);
        var allComponents = entity.getComponents();
        assertNotNull(allComponents);
        assertTrue(allComponents.size() >= desiredComponentClasses.size(),
                "entity only had " + allComponents.size() + " components, wish to test at least " + desiredComponentClasses.size() + " classes!");
        final Set<Class<? extends Component>> presentComponentClasses = new HashSet<>();
        allComponents.forEach(component -> presentComponentClasses.add(component.getClass()));
        assertTrue(presentComponentClasses.containsAll(desiredComponentClasses), "components mismatch! " +
                "\ndesired: " + classNames(desiredComponentClasses) + "\nactual: " + classNames(presentComponentClasses)
        );
    }


    private static String classNames(Collection<Class<? extends Component>> classes) {
        return classes.stream().map(Class::getSimpleName).collect(Collectors.joining(", "));
    }
}
