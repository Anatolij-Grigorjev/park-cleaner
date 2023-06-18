package com.tiem625.parkcleaner.components;

import com.badlogic.ashley.core.Component;
import com.tiem625.parkcleaner.domain.GameKey;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerInputComponent implements Component {

    private static final Map<GameKey, Boolean> KEYPRESS_MAP = Stream.<String>of(

            ).map(GameKey::new).map(gameKey -> Map.entry(gameKey, false))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    public void pressKey(GameKey key) {
        KEYPRESS_MAP.put(key, true);
    }

    public void releaseKey(GameKey key) {
        KEYPRESS_MAP.put(key, false);
    }

    public boolean isKeyPressed(GameKey key) {
        return KEYPRESS_MAP.get(key);
    }
}
