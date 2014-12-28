package com.game.simonsays;

import com.game.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimonSays extends Game {

    private static final int MAX_PATTERN_LENGTH = 10;
    private static final int ONE_SECOND_IN_MILLIS = 1000;

    private List<Pattern> patternList;
    private GameStatus gameStatus;

    private SimonSays(List<Pattern> patternList, GameStatus gameStatus) {
        this.patternList = patternList;
        this.gameStatus = gameStatus;
    }

    public static SimonSays newGame() {
        List<Pattern> patternList = new ArrayList<>();
        GameStatus gameStatus = GameStatus.NOT_FINISHED;
        return new SimonSays(patternList, gameStatus);
    }

    public List<Pattern> getPatternList() {
        return patternList;
    }

    public int getPatternLength() {
        return getPatternList().size();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    protected boolean isGameNotFinished() {
        return getGameStatus() == GameStatus.NOT_FINISHED;
    }

    @Override
    protected void printGameResults() {
        System.out.println(getGameStatus());
    }

    @Override
    protected void printGameBoard() {
        createPatternList();
        System.out.print(buildPatternString());
        createPause();
        printPatternPlaceholders();
    }

    @Override
    protected void getPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        String playerInput = scanner.next();
        updateGameStatus(playerInput);
    }

    private void createPatternList() {
        int patternLength = getPatternLength();
        List<Pattern> patternList = new ArrayList<>();
        for (int i=0; i<patternLength + 1; i++) {
            Pattern pattern = Pattern.getRandomPattern();
            patternList.add(pattern);
        }
        this.patternList = patternList;
    }

    private void createPause() {
        try {
            Thread.sleep(ONE_SECOND_IN_MILLIS * getPatternLength());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void printPatternPlaceholders() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<getPatternLength(); i++) {
            sb.append("?");
        }
        System.out.println("\r" + sb.toString());
    }

    private String buildPatternString() {
        StringBuilder sb = new StringBuilder();
        for (Pattern pattern : getPatternList()) {
            sb.append(pattern.getCode());
        }
        return sb.toString();
    }

    private void updateGameStatus(String playerInput) {
        String patternString = buildPatternString();
        boolean isInputPatternMatching = patternString.equalsIgnoreCase(playerInput);
        if (isInputPatternMatching && getPatternLength() == MAX_PATTERN_LENGTH) {
            this.gameStatus = GameStatus.PLAYER_WON;
        } else if (isInputPatternMatching) {
            this.gameStatus = GameStatus.NOT_FINISHED;
        } else {
            this.gameStatus = GameStatus.PLAYER_LOST;
        }
    }

    private enum GameStatus {
        NOT_FINISHED("Game is not finished"),
        PLAYER_WON("You won the game"),
        PLAYER_LOST("You lost the game");

        private String message;

        private GameStatus(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return getMessage();
        }
    }
}