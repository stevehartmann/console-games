package com.game.tictactoe;

import com.game.Game;

import java.util.Scanner;

import static com.game.tictactoe.Player.Values.PLAYER1_MARK;
import static com.game.tictactoe.Player.Values.PLAYER2_MARK;

public class TicTacToe extends Game {

    private Board board;
    private Player currentPlayer;
    private GameStatus gameStatus;

    private TicTacToe(Board board, Player currentPlayer, GameStatus gameStatus) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.gameStatus = gameStatus;
    }

    public static TicTacToe newGame() {
        Board board = Board.createBoard();
        Player player = Player.getRandomPlayer();
        GameStatus status = GameStatus.NOT_FINISHED;
        return new TicTacToe(board, player, status);
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    @Override
    protected void printGameBoard() {
        getBoard().printBoard();
    }

    @Override
    protected void printGameResults() {
        printGameBoard();
        System.out.println(getGameStatus());
    }

    @Override
    protected boolean isGameNotFinished() {
        return getGameStatus() == GameStatus.NOT_FINISHED;
    }

    @Override
    protected void getPlayerInput() {
        Scanner scanner = new Scanner(System.in);
        boolean isMoveAdded;
        int move;
        do {
            while (!scanner.hasNextInt()) {
                printPlayerInputErrorMessage();
                scanner.next();
            }
            move = scanner.nextInt();
            isMoveAdded = getBoard().addMoveIfAvailable(getCurrentPlayer(), move);
            if (!isMoveAdded) {
                printPlayerMoveErrorMessage();
            }
        } while (!isMoveAdded);
        updateGameStatus();
    }

    private void printPlayerInputErrorMessage() {
        System.out.println("That is not a valid option!");
        System.out.println("Please enter a number 0-8!");
    }

    private void printPlayerMoveErrorMessage() {
        System.out.println("That spot is not available!");
        System.out.println("Please choose another spot!");
    }

    private void toggleCurrentPlayer() {
        this.currentPlayer = (currentPlayer == Player.PLAYER1) ? Player.PLAYER2 : Player.PLAYER1;
    }

    private void updateGameStatus() {
        GameStatus gameStatus = GameStatus.NOT_FINISHED;
        if (getBoard().isWinner(Player.PLAYER1)) {
            gameStatus = GameStatus.PLAYER1_WON;
        } else if (getBoard().isWinner(Player.PLAYER2)) {
            gameStatus = GameStatus.PLAYER2_WON;
        } else if (getBoard().isBoardFull()) {
            gameStatus = GameStatus.DRAW;
        } else {
            toggleCurrentPlayer();
        }
        this.gameStatus = gameStatus;
    }

    private enum GameStatus {
        NOT_FINISHED("Game is not finished"),
        PLAYER1_WON(PLAYER1_MARK + " won the game"),
        PLAYER2_WON(PLAYER2_MARK + " won the game"),
        DRAW("The game was a draw");

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