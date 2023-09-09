package com.tiem625.parkcleaner.ecs;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.Optional;

public class EntitySupport extends Entity {

    public <T extends Component> Optional<T> findComponent(Class<T> componentClazz) {
        return Optional.ofNullable(getComponent(componentClazz));
    }

    public void process() {
        ECS.engine().addEntity(this);
    }

    public void stopProcessing() {
        ECS.engine().removeEntity(this);
    }
}
