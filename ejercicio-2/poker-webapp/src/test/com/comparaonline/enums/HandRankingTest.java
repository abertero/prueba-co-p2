package com.comparaonline.enums;

import com.comparaonline.beans.Card;
import com.comparaonline.beans.Hand;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class HandRankingTest {

    @Test
    public void testRoyalFlush() {
        Hand royalFlushHand = createTestHand(
                new Card(Suit.CLUBS, CardValue.ACE),
                new Card(Suit.CLUBS, CardValue.KING),
                new Card(Suit.CLUBS, CardValue.QUEEN),
                new Card(Suit.CLUBS, CardValue.JACK),
                new Card(Suit.CLUBS, CardValue.TEN)
        );
        Assert.assertEquals(HandRanking.ranksHand(royalFlushHand), HandRanking.ROYAL_FLUSH);
    }

    @Test
    public void testStraightFlush() {
        Hand straightFlush1 = createTestHand(
                new Card(Suit.CLUBS, CardValue.ACE),
                new Card(Suit.CLUBS, CardValue.KING),
                new Card(Suit.CLUBS, CardValue.QUEEN),
                new Card(Suit.CLUBS, CardValue.JACK),
                new Card(Suit.CLUBS, CardValue.TWO)
        );
        Hand straightFlush2 = createTestHand(
                new Card(Suit.HEARTS, CardValue.FIVE),
                new Card(Suit.HEARTS, CardValue.SIX),
                new Card(Suit.HEARTS, CardValue.SEVEN),
                new Card(Suit.HEARTS, CardValue.FOUR),
                new Card(Suit.HEARTS, CardValue.EIGHT)
        );
        Assert.assertEquals(HandRanking.ranksHand(straightFlush1), HandRanking.STRAIGHT_FLUSH);
        Assert.assertEquals(HandRanking.ranksHand(straightFlush2), HandRanking.STRAIGHT_FLUSH);
    }

    @Test
    public void testFourOfAKind() {
        Hand fourOfAKindHand = createTestHand(
                new Card(Suit.CLUBS, CardValue.TEN),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.CLUBS, CardValue.EIGHT)
        );
        Assert.assertEquals(HandRanking.ranksHand(fourOfAKindHand), HandRanking.FOUR_OF_A_KIND);
    }

    @Test
    public void testFullHouse() {
        Hand fullHouseHand = createTestHand(
                new Card(Suit.CLUBS, CardValue.TEN),
                new Card(Suit.HEARTS, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.EIGHT),
                new Card(Suit.CLUBS, CardValue.EIGHT)
        );
        Assert.assertEquals(HandRanking.ranksHand(fullHouseHand), HandRanking.FULL_HOUSE);
    }

    @Test
    public void testFlush() {
        Hand flushHand = createTestHand(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.SPADES, CardValue.ACE),
                new Card(Suit.SPADES, CardValue.FOUR),
                new Card(Suit.SPADES, CardValue.SEVEN),
                new Card(Suit.SPADES, CardValue.EIGHT)
        );
        Assert.assertEquals(HandRanking.ranksHand(flushHand), HandRanking.FLUSH);
    }

    @Test
    public void testStraight() {
        Hand straightHand = createTestHand(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.NINE),
                new Card(Suit.CLUBS, CardValue.EIGHT),
                new Card(Suit.HEARTS, CardValue.JACK),
                new Card(Suit.SPADES, CardValue.SEVEN)
        );
        Assert.assertEquals(HandRanking.ranksHand(straightHand), HandRanking.STRAIGHT);
    }

    @Test
    public void testThreeOfAKind() {
        Hand threeOfAKindHand = createTestHand(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.NINE),
                new Card(Suit.CLUBS, CardValue.NINE),
                new Card(Suit.HEARTS, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.SEVEN)
        );
        Assert.assertEquals(HandRanking.ranksHand(threeOfAKindHand), HandRanking.THREE_OF_A_KIND);
    }

    @Test
    public void testTwoPairs() {
        Hand twoPairsHand = createTestHand(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.TEN),
                new Card(Suit.CLUBS, CardValue.NINE),
                new Card(Suit.HEARTS, CardValue.NINE),
                new Card(Suit.SPADES, CardValue.SEVEN)
        );
        Assert.assertEquals(HandRanking.ranksHand(twoPairsHand), HandRanking.TWO_PAIRS);
    }

    @Test
    public void testOnePair() {
        Hand onePairHand = createTestHand(
                new Card(Suit.SPADES, CardValue.TEN),
                new Card(Suit.DIAMONDS, CardValue.TEN),
                new Card(Suit.CLUBS, CardValue.NINE),
                new Card(Suit.HEARTS, CardValue.EIGHT),
                new Card(Suit.SPADES, CardValue.SEVEN)
        );
        Assert.assertEquals(HandRanking.ranksHand(onePairHand), HandRanking.ONE_PAIR);
    }

    @Test
    public void testHighCard() {
        Hand highCardHand = createTestHand(
                new Card(Suit.SPADES, CardValue.KING),
                new Card(Suit.DIAMONDS, CardValue.TEN),
                new Card(Suit.CLUBS, CardValue.NINE),
                new Card(Suit.HEARTS, CardValue.EIGHT),
                new Card(Suit.SPADES, CardValue.SEVEN)
        );
        Assert.assertEquals(HandRanking.ranksHand(highCardHand), HandRanking.HIGH_CARD);
    }

    public static Hand createTestHand(Card card1, Card card2, Card card3, Card card4, Card card5) {
        return new Hand(Arrays.asList(new Card[]{card1, card2, card3, card4, card5}));
    }
}
