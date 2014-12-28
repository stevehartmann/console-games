package com.game.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.game.blackjack.Card.Rank;
import static com.game.blackjack.Card.Suit;

public class Deck {

    private static final int MIN_NUMBER_OF_DECKS = 1;
    private static final int MAX_NUMBER_OF_DECKS = 8;

    private List<Card> cards = new ArrayList<>();

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeck() {
        return createDeck(3);
    }

    public static Deck createDeck(int numberOfDecks) {
        if (numberOfDecks < MIN_NUMBER_OF_DECKS || numberOfDecks > MAX_NUMBER_OF_DECKS) {
            String decksRange = MIN_NUMBER_OF_DECKS + "-" + MAX_NUMBER_OF_DECKS;
            throw new IllegalArgumentException("Only " + decksRange + " decks are currently supported");
        }
        List<Card> cards = new ArrayList<>();
        for (int i=MIN_NUMBER_OF_DECKS; i<=numberOfDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(rank, suit));
                }
            }
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public Card drawCard() {
        if (!isCardFound()) {
            throw new IllegalStateException("There are no cards left to draw");
        }
        int selectedIndex = new Random().nextInt(getCardsCount() - 1);
        Card selectedCard = getCards().get(selectedIndex);
        getCards().remove(selectedCard);
        return selectedCard;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isCardFound() {
        return !getCards().isEmpty();
    }

    public Integer getCardsCount() {
        return getCards().size();
    }
}