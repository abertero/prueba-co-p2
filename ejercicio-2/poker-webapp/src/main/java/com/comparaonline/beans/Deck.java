package com.comparaonline.beans;

import com.comparaonline.enums.CardValue;
import com.comparaonline.enums.Suit;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck(final List<Card> cards) {
        this.cards = cards;
    }

    public Deck(final JsonNode node) {
        Iterator<JsonNode> elements = node.getElements();
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            cards.add(new Card(element));
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return String.format("Deck{cards=[%s]}", StringUtils.join(cards, ","));
    }

    public static Deck createLocal() {
        List<Card> cards = new ArrayList<>();
        for (CardValue cardValue : CardValue.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(suit, cardValue));
            }
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
