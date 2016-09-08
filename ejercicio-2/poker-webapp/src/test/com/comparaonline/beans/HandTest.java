package com.comparaonline.beans;

import com.comparaonline.enums.CardValue;
import com.comparaonline.enums.HandRankingTest;
import com.comparaonline.enums.Suit;
import org.junit.Assert;
import org.junit.Test;

public class HandTest {
    @Test
    public void compareRoyalFlushHands() {
        Hand hand1 = HandRankingTest.createTestHand(
                new Card(Suit.HEARTS, CardValue.ACE),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.HEARTS, CardValue.JACK),
                new Card(Suit.HEARTS, CardValue.QUEEN),
                new Card(Suit.HEARTS, CardValue.KING)
        );
        Hand hand2 = HandRankingTest.createTestHand(
                new Card(Suit.SPADES, CardValue.ACE),
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.JACK),
                new Card(Suit.SPADES, CardValue.QUEEN),
                new Card(Suit.SPADES, CardValue.KING)
        );
        Assert.assertEquals(hand1.compareTo(hand2), 0);
    }

    @Test
    public void compareStraightFlushHands() {
        Hand hand1 = HandRankingTest.createTestHand(
                new Card(Suit.HEARTS, CardValue.NINE),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.HEARTS, CardValue.JACK),
                new Card(Suit.HEARTS, CardValue.SEVEN),
                new Card(Suit.HEARTS, CardValue.EIGHT)
        );
        Hand hand2 = HandRankingTest.createTestHand(
                new Card(Suit.SPADES, CardValue.THREE),
                new Card(Suit.SPADES, CardValue.FOUR),
                new Card(Suit.SPADES, CardValue.FIVE),
                new Card(Suit.SPADES, CardValue.SIX),
                new Card(Suit.SPADES, CardValue.SEVEN)
        );
        Assert.assertEquals(hand1.compareTo(hand2), 1);
    }
}
