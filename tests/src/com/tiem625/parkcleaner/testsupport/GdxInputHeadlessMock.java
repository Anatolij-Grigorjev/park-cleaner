package com.tiem625.parkcleaner.testsupport;

import com.badlogic.gdx.backends.headless.mock.input.MockInput;

import java.util.HashSet;
import java.util.Set;

public class GdxInputHeadlessMock extends MockInput {

    private final Set<Integer> pressedKeys = new HashSet<>();

    @Override
    public boolean isKeyPressed(int key) {
        return pressedKeys.contains(key);
    }

    public void setKeyPressed(int keyCode) {
        pressedKeys.add(keyCode);
    }

    public void clearKeyPressed(int keyCode) {
        pressedKeys.remove(keyCode);
    }
}
