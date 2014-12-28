package com.game.simonsays;

import java.util.Random;

public enum Pattern {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E");

    private String code;

    private Pattern(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Pattern getRandomPattern() {
        switch(new Random().nextInt(values().length - 1)) {
            case 0: return A;
            case 1: return B;
            case 2: return C;
            case 3: return D;
            default: return E;
        }
    }

    @Override
    public String toString() {
        return getCode();
    }
}