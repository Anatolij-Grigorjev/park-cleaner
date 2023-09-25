package com.tiem625.parkcleaner.domain;

import com.badlogic.gdx.Input;

public record GameKey(String actionName, int keyBinding) {

    public static final GameKey TEST_ACTION = new GameKey("TestAction", Input.Keys.T);
}
