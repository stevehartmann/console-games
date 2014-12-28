package com.game.blackjack;

import java.util.ArrayList;
import java.util.List;

public class User {

    private List<Card> cards = new ArrayList<>();
    private UserStatus status;

    public List<Card> getCards() {
        return cards;
    }

    public UserStatus getStatus() {
        return status;
    }

    public boolean isBusted() {
        return getStatus() == UserStatus.BUSTED;
    }

    public boolean isBlackJack() {
        return getStatus() == UserStatus.BLACKJACK;
    }

    public boolean isInStayRange() {
        return getStatus() == UserStatus.IN_STAY_RANGE;
    }

    public boolean isBelowStayRange() {
        return getStatus() == UserStatus.BELOW_STAY_RANGE;
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("User card must not be null");
        }
        getCards().add(card);
        this.status = calculateStatus(getTotalCardsValue());
    }

    public Integer getTotalCardsValue() {
        Integer totalCardsValue = 0;
        boolean isAceInCards = false;
        for (Card card : getCards()) {
            if (card.isAce()) {
                isAceInCards = true;
            }
            totalCardsValue += card.getValue();
        }
        if (isAceInCards) {
            Integer alternateTotalCardsValue = totalCardsValue + Card.ACE_ALTERNATE_VALUE - 1;
            if (calculateStatus(alternateTotalCardsValue) != UserStatus.BUSTED) {
                return alternateTotalCardsValue;
            }
        }
        return totalCardsValue;
    }

    private UserStatus calculateStatus(Integer totalCardsValue) {
        if (totalCardsValue > BlackJack.BLACKJACK_TOTAL) {
            return UserStatus.BUSTED;
        } else if (totalCardsValue == BlackJack.BLACKJACK_TOTAL) {
            return UserStatus.BLACKJACK;
        } else if (totalCardsValue >= BlackJack.STAY_LOWER_LIMIT) {
            return UserStatus.IN_STAY_RANGE;
        } else {
            return UserStatus.BELOW_STAY_RANGE;
        }
    }

    private enum UserStatus {
        BUSTED,
        BLACKJACK,
        IN_STAY_RANGE,
        BELOW_STAY_RANGE
    }
}