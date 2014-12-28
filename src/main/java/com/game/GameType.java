package com.game;

import com.game.blackjack.BlackJack;
import com.game.simonsays.SimonSays;
import com.game.tictactoe.TicTacToe;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum GameType {
    TIC_TAC_TOE(0, "Tic Tac Toe", TicTacToe.newGame()),
    SIMON_SAYS(1, "Simon Says", SimonSays.newGame()),
    BLACK_JACK(2, "Black Jack", BlackJack.newGame());

    private static final Map<Integer, GameType> REVERSE_LOOKUP_MAP = new HashMap<>();
    static {
        for (GameType gameType : values()) {
            REVERSE_LOOKUP_MAP.put(gameType.getCode(), gameType);
        }
    }

    private Integer code;
    private String description;
    private Game game;

    private GameType(Integer code, String description, Game game) {
        this.code = code;
        this.description = description;
        this.game = game;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Game getGame() {
        return game;
    }

    public static Game getRandomGame() {
        int selectedIndex = new Random().nextInt(values().length);
        GameType gameType = REVERSE_LOOKUP_MAP.get(selectedIndex);
        return gameType.getGame();
    }

    @Override
    public String toString() {
        return getDescription();
    }
}