package com.game.tictactoe;

import java.util.Random;

public enum Player {
    PLAYER1(Values.PLAYER1_MARK),
    PLAYER2(Values.PLAYER2_MARK);

    private String mark;

    private Player(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public static Player getRandomPlayer() {
        switch (new Random().nextInt(values().length - 1)) {
            case 0: return PLAYER1;
            default: return PLAYER2;
        }
    }

    @Override
    public String toString() {
        return getMark();
    }

    public interface Values {
        String PLAYER1_MARK = "+";
        String PLAYER2_MARK = "*";
    }
}