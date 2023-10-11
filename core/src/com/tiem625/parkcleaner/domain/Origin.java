package com.tiem625.parkcleaner.domain;

public record Origin(Position position, Scale scale, Rotation rotation) {

    public static final Origin DEFAULT = new Origin(Position.ZERO, Scale.ORIGINAL, Rotation.NONE);
}
