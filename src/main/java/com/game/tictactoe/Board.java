package com.game.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 3;
    private static final int TOTAL_MOVES_ALLOWED = ROWS_COUNT * COLUMNS_COUNT;

    private final List<Player> gameBoard;
    private int totalMovesPlayed = 0;

    private Board(int gameBoardSize) {
        this.gameBoard = new ArrayList<>(gameBoardSize);
        for(int i=0; i<TOTAL_MOVES_ALLOWED; i++) {
            this.gameBoard.add(null);
        }
    }

    public static Board createBoard() {
        return new Board(TOTAL_MOVES_ALLOWED);
    }

    public List<Player> getGameBoard() {
        return gameBoard;
    }

    public int getTotalMovesPlayed() {
        return totalMovesPlayed;
    }

    public boolean isBoardFull() {
        return getTotalMovesPlayed() == TOTAL_MOVES_ALLOWED;
    }

    public boolean addMoveIfAvailable(Player player, int move) {
        if (move < 0 || move >= TOTAL_MOVES_ALLOWED) {
            throw new IllegalStateException("Move must be 0-8");
        }
        if (null == getGameBoardSpot(move)) {
            setGameBoardSpot(player, move);
            this.totalMovesPlayed++;
            return true;
        }
        return false;
    }

    public boolean isWinner(Player player) {
        return isRowWinner(player) || isColumnWinner(player) || isDiagonalWinner(player);
    }

    public void printBoard() {
        for (int row = 0; row < ROWS_COUNT; row++) {
            for (int column = 0; column < COLUMNS_COUNT; column++) {
                int spot = ROWS_COUNT * row + column;
                Player spotValue = getGameBoardSpot(spot);
                System.out.print((spotValue != null) ? spotValue : spot);
            }
            System.out.print("\n");
        }
    }

    private Player getGameBoardSpot(int spot) {
        return getGameBoard().get(spot);
    }

    private Player setGameBoardSpot(Player player, int move) {
        return getGameBoard().set(move, player);
    }

    private boolean isGameBoardSpotsEqual(Player player, int... spots) {
        for (int spot : spots) {
            if (getGameBoardSpot(spot) != player) {
                return false;
            }
        }
        return true;
    }

    private boolean isRowWinner(Player player) {
        for (int row=0; row<ROWS_COUNT; row++) {
            if (isGameBoardSpotsEqual(player, ROWS_COUNT * row, ROWS_COUNT * row + 1, ROWS_COUNT * row + 2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isColumnWinner(Player player) {
        for (int column=0; column<COLUMNS_COUNT; column++) {
            if (isGameBoardSpotsEqual(player, column, COLUMNS_COUNT + column, COLUMNS_COUNT * 2 + column)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWinner(Player player) {
        return isGameBoardSpotsEqual(player, 0, 4, 8) || isGameBoardSpotsEqual(player, 2, 4, 6);
    }
}