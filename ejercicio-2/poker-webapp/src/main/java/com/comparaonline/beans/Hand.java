package com.comparaonline.beans;

import com.comparaonline.enums.CardValue;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Hand {
    public static final int HAND_SIZE = 5;

    private SortedSet<Card> cards = new TreeSet<>();

    public Hand(List<Card> cards) {
        if (cards != null && cards.size() == HAND_SIZE) {
            this.cards.addAll(cards);
        }
    }

    public SortedSet<Card> getCards() {
        return cards;
    }

    public CardValue getHighCardValue() {
        if (hasAce()) {
            return CardValue.ACE;
        } else {
            Card lastCard = cards.last();
            return cards.isEmpty() || lastCard == null ? null : lastCard.getNumber();
        }
    }

    public boolean hasAce() {
        if (cards.isEmpty()) {
            return false;
        }
        Card firstCard = cards.first();
        return firstCard.getNumber() != null && firstCard.getNumber() == CardValue.ACE;
    }
}
