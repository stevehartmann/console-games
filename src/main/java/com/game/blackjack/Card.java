package com.game.blackjack;

public class Card {

    public static final Integer ACE_ALTERNATE_VALUE = 11;

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Integer getValue() {
        return getRank().getValue();
    }

    public boolean isAce() {
        return getRank() == Rank.ACE;
    }

    public boolean isFaceCard() {
        return getRank() == Rank.JACK || getRank() == Rank.QUEEN || getRank() == Rank.KING;
    }

    @Override
    public String toString() {
        return getRank() + " of " + getSuit();
    }

    public enum Rank {
        ACE(1, "Ace"),
        TWO(2, "Two"),
        THREE(3, "Three"),
        FOUR(4, "Four"),
        FIVE(5, "Five"),
        SIX(6, "Six"),
        SEVEN(7, "Seven"),
        EIGHT(8, "Eight"),
        NINE(9, "Nine"),
        TEN(10, "Ten"),
        JACK(10, "Jack"),
        QUEEN(10, "Queen"),
        KING(10, "King");

        private Integer value;
        private String description;

        private Rank(Integer value, String description) {
            this.value = value;
            this.description = description;
        }

        public Integer getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return getDescription();
        }
    }

    public enum Suit {
        DIAMONDS("Diamonds"),
        SPADES("Spades"),
        HEARTS("Hearts"),
        CLUBS("Clubs");

        private String description;

        private Suit(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return getDescription();
        }
    }
}