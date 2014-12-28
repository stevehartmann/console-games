package com.game;

public abstract class Game {

    public final void playGame() {
        do {
            printGameBoard();
            getPlayerInput();
        } while (isGameNotFinished());
        printGameResults();
    }

    protected abstract void printGameBoard();

    protected abstract void getPlayerInput();

    protected abstract boolean isGameNotFinished();

    protected abstract void printGameResults();

}