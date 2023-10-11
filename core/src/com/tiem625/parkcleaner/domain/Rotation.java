package com.tiem625.parkcleaner.domain;

public record Rotation(float amount, Direction direction) {

    public static final Rotation NONE = Rotation.ofDirectedAmount(0f);

    public static Rotation ofDirectedAmount(float directedRotation) {
        return new Rotation(Math.abs(directedRotation), Direction.ofRotation(directedRotation));
    }

    public float directed() {
        return amount * direction.amountMultiplier;
    }

    public enum Direction {
        CW(-1),
        CCW(1);

        private final int amountMultiplier;

        Direction(int amountMultiplier) {
            this.amountMultiplier = amountMultiplier;
        }

        public static Direction ofRotation(float rotation) {
            return Math.signum(rotation) < 0 ? CW : CCW;
        }
    }
}
