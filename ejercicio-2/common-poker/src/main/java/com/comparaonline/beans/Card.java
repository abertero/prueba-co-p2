package com.comparaonline.beans;

import com.comparaonline.enums.CardValue;
import com.comparaonline.enums.Suit;

public class Card {
    private Suit suit;
    private CardValue value;

    private Card(Suit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    public static Card fromNumberAndSuit(String numberDisplay, String suitDisplay) {
        return new Card(Suit.fromDisplay(suitDisplay), CardValue.fromDisplay(numberDisplay));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            Card card = (Card) obj;
            return suit == card.getSuit() && value == card.getValue();
        }
    }
}
