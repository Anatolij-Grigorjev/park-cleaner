package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.tiem625.parkcleaner.Inputs;
import com.tiem625.parkcleaner.domain.GameKey;

import java.util.Map;
import java.util.stream.Collectors;

public class PlayerInputComponent implements Component {

    private static final Map<GameKey, Boolean> KEYPRESS_MAP = Inputs.INPUT_MAPPINGS
            .keySet().stream()
            .map(gameKey -> Map.entry(gameKey, false))
            .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));

    public void pressKey(GameKey key) {
        KEYPRESS_MAP.put(key, true);
    }

    public void releaseKey(GameKey key) {
        KEYPRESS_MAP.put(key, false);
    }

    public boolean isKeyPressed(GameKey key) {
        return KEYPRESS_MAP.get(key);
    }

    public void releaseAllKeys() {
        KEYPRESS_MAP.keySet().forEach(this::releaseKey);
    }
}
