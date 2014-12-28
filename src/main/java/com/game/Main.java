package com.game;

public class Main {
    public static void main(String[] args) {
        Game game = GameType.getRandomGame();
        game.playGame();
    }
}