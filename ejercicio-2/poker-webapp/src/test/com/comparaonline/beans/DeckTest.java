package com.comparaonline.beans;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class DeckTest {

    @Test
    public void testDeckSize() {
        Deck localDeck = Deck.createLocal();
        Assert.assertEquals(localDeck.getCards().size(), Deck.DECK_SIZE);
        Assert.assertEquals(localDeck.getPosition(), 0);
    }

    @Test
    public void testCardUniqueness() {
        Deck localDeck = Deck.createLocal();
        Set<String> uniqueSetOfCards = new HashSet<>();
        for (Card card : localDeck.getCards()) {
            String cardString = card.toString();
            if (uniqueSetOfCards.contains(cardString)) {
                Assert.fail(String.format("Carta no unica: %s, Cartas contadas: %s", cardString, uniqueSetOfCards));
            } else {
                uniqueSetOfCards.add(cardString);
            }
        }
    }

    @Test
    public void testHandSize() {
        Deck localDeck = Deck.createLocal();
        Set<String> uniqueSetOfCards = new HashSet<>();
        for (int i = 0; i < 10; ++i) {
            Hand hand = localDeck.getHand();
            Assert.assertEquals(hand.getCards().size(), Hand.HAND_SIZE);
            Assert.assertEquals(localDeck.getPosition(), Hand.HAND_SIZE * (i + 1));
            for (Card card : hand.getCards()) {
                String cardString = card.toString();
                if (uniqueSetOfCards.contains(cardString)) {
                    Assert.fail(String.format("Carta no unica: %s, Cartas contadas: %s", cardString, uniqueSetOfCards));
                } else {
                    uniqueSetOfCards.add(cardString);
                }
            }
        }
        Assert.assertNull(localDeck.getHand());
    }
}
