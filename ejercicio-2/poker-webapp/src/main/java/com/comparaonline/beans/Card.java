package com.comparaonline.beans;

import com.comparaonline.enums.CardValue;
import com.comparaonline.enums.Suit;
import org.codehaus.jackson.JsonNode;

public class Card {
    private Suit suit;
    private CardValue number;

    public Card(final Suit suit, final CardValue number) {
        this.suit = suit;
        this.number = number;
    }

    public Card(final JsonNode node) {
        String suit = node.get("suit").asText();
        String number = node.get("number").asText();
        this.suit = Suit.fromDisplay(suit);
        this.number = CardValue.fromDisplay(number);
    }

    public Suit getSuit() {
        return suit;
    }

    public CardValue getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        } else {
            Card card = (Card) obj;
            return suit == card.getSuit() && number == card.getNumber();
        }
    }

    @Override
    public String toString() {
        return String.format("%s-%s", number.getDisplay(), suit.getDisplay());
    }
}
