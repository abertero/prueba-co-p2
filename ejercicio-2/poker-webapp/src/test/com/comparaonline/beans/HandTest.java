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

    @Test
    public void compareFourOfAKindHands() {
        Hand hand1 = HandRankingTest.createTestHand(
                new Card(Suit.HEARTS, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.NINE),
                new Card(Suit.CLUBS, CardValue.NINE),
                new Card(Suit.DIAMONDS, CardValue.NINE),
                new Card(Suit.HEARTS, CardValue.EIGHT)
        );
        Hand hand2 = HandRankingTest.createTestHand(
                new Card(Suit.SPADES, CardValue.ACE),
                new Card(Suit.CLUBS, CardValue.ACE),
                new Card(Suit.DIAMONDS, CardValue.ACE),
                new Card(Suit.HEARTS, CardValue.ACE),
                new Card(Suit.SPADES, CardValue.SEVEN)
        );
        Assert.assertEquals(hand1.compareTo(hand2), -1);
    }

    @Test
    public void compareFullHouseHands() {
        Hand hand1 = HandRankingTest.createTestHand(
                new Card(Suit.HEARTS, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.NINE),
                new Card(Suit.CLUBS, CardValue.NINE),
                new Card(Suit.DIAMONDS, CardValue.EIGHT),
                new Card(Suit.HEARTS, CardValue.EIGHT)
        );
        Hand hand2 = HandRankingTest.createTestHand(
                new Card(Suit.SPADES, CardValue.SEVEN),
                new Card(Suit.CLUBS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.KING),
                new Card(Suit.HEARTS, CardValue.KING),
                new Card(Suit.SPADES, CardValue.SEVEN)
        );
        Assert.assertEquals(hand1.compareTo(hand2), 1);
    }

    @Test
    public void compareFlushHands() {
        Hand hand1 = HandRankingTest.createTestHand(
                new Card(Suit.CLUBS, CardValue.NINE),
                new Card(Suit.CLUBS, CardValue.TEN),
                new Card(Suit.CLUBS, CardValue.TWO),
                new Card(Suit.CLUBS, CardValue.EIGHT),
                new Card(Suit.CLUBS, CardValue.FIVE)
        );
        Hand hand2 = HandRankingTest.createTestHand(
                new Card(Suit.DIAMONDS, CardValue.EIGHT),
                new Card(Suit.DIAMONDS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.KING),
                new Card(Suit.DIAMONDS, CardValue.FOUR),
                new Card(Suit.DIAMONDS, CardValue.FIVE)
        );
        Assert.assertEquals(hand1.compareTo(hand2), -1);
    }

    @Test
    public void compareFlushEqualHands() {
        Hand hand1 = HandRankingTest.createTestHand(
                new Card(Suit.CLUBS, CardValue.EIGHT),
                new Card(Suit.CLUBS, CardValue.SEVEN),
                new Card(Suit.CLUBS, CardValue.KING),
                new Card(Suit.CLUBS, CardValue.FOUR),
                new Card(Suit.CLUBS, CardValue.FIVE)
        );
        Hand hand2 = HandRankingTest.createTestHand(
                new Card(Suit.DIAMONDS, CardValue.EIGHT),
                new Card(Suit.DIAMONDS, CardValue.SEVEN),
                new Card(Suit.DIAMONDS, CardValue.KING),
                new Card(Suit.DIAMONDS, CardValue.FOUR),
                new Card(Suit.DIAMONDS, CardValue.FIVE)
        );
        Assert.assertEquals(hand1.compareTo(hand2), 0);
    }

    @Test
    public void compareStraightHand() {
        Hand hand1 = HandRankingTest.createTestHand(
                new Card(Suit.CLUBS, CardValue.EIGHT),
                new Card(Suit.HEARTS, CardValue.SEVEN),
                new Card(Suit.SPADES, CardValue.SIX),
                new Card(Suit.CLUBS, CardValue.FOUR),
                new Card(Suit.CLUBS, CardValue.FIVE)
        );
        Hand hand2 = HandRankingTest.createTestHand(
                new Card(Suit.DIAMONDS, CardValue.EIGHT),
                new Card(Suit.CLUBS, CardValue.SEVEN),
                new Card(Suit.HEARTS, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.JACK)
        );
        Assert.assertEquals(hand1.compareTo(hand2), -1);
    }

    @Test
    public void compareThreeOfAKind() {
        Hand hand1 = HandRankingTest.createTestHand(
                new Card(Suit.CLUBS, CardValue.EIGHT),
                new Card(Suit.HEARTS, CardValue.SIX),
                new Card(Suit.SPADES, CardValue.SIX),
                new Card(Suit.CLUBS, CardValue.SIX),
                new Card(Suit.CLUBS, CardValue.FIVE)
        );
        Hand hand2 = HandRankingTest.createTestHand(
                new Card(Suit.DIAMONDS, CardValue.EIGHT),
                new Card(Suit.CLUBS, CardValue.SEVEN),
                new Card(Suit.HEARTS, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.NINE),
                new Card(Suit.DIAMONDS, CardValue.NINE)
        );
        Assert.assertEquals(hand1.compareTo(hand2), -1);
    }

}
