package com.tiem625.parkcleaner;

import com.tiem625.parkcleaner.domain.GameKey;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Inputs {

    private static final Set<GameKey> DEFAULT_MAPPINGS = Collections.unmodifiableSet(Set.of(
            //test key action used in tests
            GameKey.TEST_ACTION
    ));

    private static final Set<GameKey> CURRENT_MAPPINGS = new HashSet<>(DEFAULT_MAPPINGS);

    public static Set<GameKey> defaults() {
        return DEFAULT_MAPPINGS;
    }

    public static Set<GameKey> current() {
        return CURRENT_MAPPINGS;
    }

    public static void resetDefaults() {
        CURRENT_MAPPINGS.clear();
        CURRENT_MAPPINGS.addAll(DEFAULT_MAPPINGS);
    }

    private Inputs() {
        throw new UnsupportedOperationException("static");
    }
}
