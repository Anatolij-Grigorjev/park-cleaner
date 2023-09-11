package com.tiem625.parkcleaner.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class EntitySupport extends Entity {

    protected transient boolean processingInEngine = false;

    public Entity addAll(Component... components) {
        for (Component component : components) {
            add(component);
        }
        return this;
    }

    public <T extends Component> Optional<T> findComponent(Class<T> componentClazz) {
        return Optional.ofNullable(getComponent(componentClazz));
    }

    public Stream<Component> components() {
        return StreamSupport.stream(getComponents().spliterator(), false);
    }

    public synchronized void process() {
        if (!processingInEngine) {
            ECS.engine().addEntity(this);
            processingInEngine = true;
        }
    }

    public synchronized void stopProcessing() {
        if (processingInEngine) {
            ECS.engine().removeEntity(this);
            processingInEngine = false;
        }
    }
}
