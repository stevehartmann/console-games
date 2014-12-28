package com.game.blackjack;

import com.game.Game;

import java.util.Scanner;

public class BlackJack extends Game {

    public static final int BLACKJACK_TOTAL = 21;
    public static final int STAY_LOWER_LIMIT = 17;

    private final Deck deck;
    private final User player;
    private final User dealer;
    private GameStatus status;

    private BlackJack(Deck deck, User player, User dealer) {
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
        this.status = GameStatus.NOT_FINISHED;
    }

    public static BlackJack newGame() {
        Deck deck = Deck.createDeck();
        User player = new User();
        User dealer = new User();
        return new BlackJack(deck, player, dealer);
    }

    public Deck getDeck() {
        return deck;
    }

    public User getPlayer() {
        return player;
    }

    public User getDealer() {
        return dealer;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Integer getPlayerTotalCardsValue() {
        return getPlayer().getTotalCardsValue();
    }

    public Integer getDealerTotalCardsValue() {
        return getDealer().getTotalCardsValue();
    }

    @Override
    public boolean isGameNotFinished() {
        return getStatus() == GameStatus.NOT_FINISHED;
    }

    @Override
    protected void printGameBoard() {
        printGameOptions();
    }

    @Override
    public void getPlayerInput() {
        getPlayerMoves();
        getDealerMoves();
    }

    @Override
    public void printGameResults() {
        printPlayerCardsAndTotalValue();
        printDealerCardsAndTotalValue();
        System.out.println(getStatus());
    }

    private void getPlayerMoves() {
        Scanner scanner = new Scanner(System.in);
        GameOption option;
        do {
            while (!scanner.hasNextInt()) {
                printPlayerInputErrorMessage();
                scanner.next();
            }
            int code = scanner.nextInt();
            option = GameOption.optionLookup(code);
            if (option == GameOption.HIT) {
                Card card = getDeck().drawCard();
                getPlayer().addCard(card);
                printPlayerCardsAndTotalValue();
                printGameOptions();
            }
        } while (option != GameOption.STAY);
        updateGameStatus();
    }

    private void getDealerMoves() {
        do {
            Card card = getDeck().drawCard();
            getDealer().addCard(card);
        } while (getDealer().isBelowStayRange());
        updateGameStatus();
    }

    private void printGameOptions() {
        for (GameOption option : GameOption.values()) {
            System.out.println(option);
        }
    }

    private void printPlayerInputErrorMessage() {
        System.out.println("That is not a valid option!");
        System.out.println("Please try again!");
    }

    private void printPlayerCardsAndTotalValue() {
        System.out.println("---------------");
        System.out.println("Player Cards:");
        for (Card card : getPlayer().getCards()) {
            System.out.println(card);
        }
        System.out.println("Total: " + getPlayerTotalCardsValue());
        System.out.println("---------------");
    }

    private void printDealerCardsAndTotalValue() {
        System.out.println("---------------");
        System.out.println("Dealer Cards:");
        for (Card card : getDealer().getCards()) {
            System.out.println(card);
        }
        System.out.println("Total: " + getDealerTotalCardsValue());
        System.out.println("---------------");
    }

    private void updateGameStatus() {
        GameStatus gameStatus = GameStatus.NOT_FINISHED;
        if ((getPlayer().isBlackJack() && getDealer().isBlackJack())
                || (getPlayer().isBusted() && getDealer().isBusted())
                || getPlayerTotalCardsValue().equals(getDealerTotalCardsValue())) {
            gameStatus = GameStatus.DRAW;
        } else if (getPlayer().isBlackJack()
                || getDealer().isBusted()
                || getPlayerTotalCardsValue() > getDealerTotalCardsValue()) {
            gameStatus = GameStatus.PLAYER_WON;
        } else if (getPlayer().isBusted()
                || getDealer().isBlackJack()
                || getPlayerTotalCardsValue() < getDealerTotalCardsValue()) {
            gameStatus = GameStatus.DEALER_WON;
        }
        this.status = gameStatus;
    }

    private enum GameOption {
        HIT(1, "Hit"),
        STAY(2, "Stay");

        private int code;
        private String description;

        private GameOption(int code, String description) {
            this.code = code;
            this.description = description;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }

        public static GameOption optionLookup(Integer code) {
            for (GameOption option : values()) {
                if (option.getCode() == code) {
                    return option;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return getCode() + " = " + getDescription();
        }
    }

    private enum GameStatus {
        NOT_FINISHED("Game is not finished"),
        PLAYER_WON("The player won the game"),
        DEALER_WON("The dealer won the game"),
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