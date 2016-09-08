package com.comparaonline.beans;

import com.comparaonline.enums.CardValue;
import com.comparaonline.enums.HandRanking;
import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Hand implements Comparable {
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

    public HandRanking getRanking() {
        return HandRanking.ranksHand(this);
    }

    /**
     * Method to compare 2 hands, if result is 1, this defeats hand, 0 if ties, and -1 if hand defeats this.
     *
     * @param obj the hand to be compared with this
     * @return 1 if this defeats hand, 0 if tie, otherwise -1.
     */
    @Override
    public int compareTo(Object obj) {
        if (obj == null) {
            return 1;
        }
        Hand hand = (Hand) obj;
        HandRanking thisRanking = getRanking();
        HandRanking handRanking = hand.getRanking();
        if (thisRanking != handRanking) {
            return thisRanking.getRanking() > handRanking.getRanking() ? 1 : -1;
        } else {
            switch (thisRanking) {
                case ROYAL_FLUSH:
                    return 0;
                case STRAIGHT_FLUSH:
                case FLUSH:
                case STRAIGHT:
                case HIGH_CARD:
                    return compareSingleCards(hand);
                case FOUR_OF_A_KIND:
                    int compareFour = compareRepeatsInHand(hand, 4);
                    return compareFour == 0 ? compareSingleCards(hand) : compareFour;
                case FULL_HOUSE:
                    int compareThree = compareRepeatsInHand(hand, 3);
                    return compareThree == 0 ? compareRepeatsInHand(hand, 2) : compareThree;
                case THREE_OF_A_KIND:
                    compareThree = compareRepeatsInHand(hand, 3);
                    return compareThree == 0 ? compareSingleCards(hand) : compareThree;
                case TWO_PAIRS:
                    int compareTwoPairs = compareTwoPairs(hand);
                    return compareTwoPairs == 0 ? compareSingleCards(hand) : compareTwoPairs;
                case ONE_PAIR:
                    int comparePair = compareRepeatsInHand(hand, 2);
                    return comparePair == 0 ? compareSingleCards(hand) : comparePair;
                default:
                    return 0;
            }
        }
    }

    private int compareRepeatsInHand(Hand hand, int expectedRepeats) {
        CardValue exactlyRepeatsInThis = HandRanking.getExactlyRepeatsInHand(this, expectedRepeats);
        CardValue exactlyRepeatsInHand = HandRanking.getExactlyRepeatsInHand(hand, expectedRepeats);
        return NumberUtils.compare(exactlyRepeatsInThis.getValue(), exactlyRepeatsInHand.getValue());
    }

    /**
     * Compara las cartas de mayor valor, hasta encontrar una que sea mayor que la otra,
     * en este caso retorna 1, 0 si son todas iguales, y -1 si la otra mano tiene una carta mayor.
     *
     * @param hand la mano a comparar con this.
     * @return 1 si this contiene una mano mayor, 0 si son iguales, -1 si hand tiene una mayor.
     */
    private int compareSingleCards(Hand hand) {
        List<Card> thisCards = new ArrayList<>(cards);
        List<Card> handCards = new ArrayList<>(hand.getCards());
        for (int i = 0; i < HAND_SIZE; ++i) {
            Card highCardValue = Card.getHighCardValue(thisCards);
            Card highCardValueInHand = Card.getHighCardValue(handCards);
            if (highCardValue.getNumber() != highCardValueInHand.getNumber()) {
                return NumberUtils.compare(highCardValue.getNumber().getValue(), highCardValueInHand.getNumber().getValue());
            }
            thisCards.remove(highCardValue);
            handCards.remove(highCardValueInHand);
        }
        return 0;
    }

    private int compareTwoPairs(Hand hand) {
        List<CardValue> twoPairsInThis = HandRanking.getTwoPairs(this);
        List<CardValue> twoPairsInHand = HandRanking.getTwoPairs(hand);
        int maxInThis, minInThis, maxInHand, minInHand;
        if (twoPairsInThis.get(0).getValue() > twoPairsInThis.get(1).getValue()) {
            maxInThis = twoPairsInThis.get(0).getValue();
            minInThis = twoPairsInThis.get(1).getValue();
        } else {
            maxInThis = twoPairsInThis.get(1).getValue();
            minInThis = twoPairsInThis.get(0).getValue();
        }
        if (twoPairsInHand.get(0).getValue() > twoPairsInHand.get(1).getValue()) {
            maxInHand = twoPairsInHand.get(0).getValue();
            minInHand = twoPairsInHand.get(1).getValue();
        } else {
            maxInHand = twoPairsInHand.get(1).getValue();
            minInHand = twoPairsInHand.get(0).getValue();
        }
        int compareMax = NumberUtils.compare(maxInThis, maxInHand);
        return compareMax == 0 ? NumberUtils.compare(minInThis, minInHand) : compareMax;
    }

    public boolean hasAce() {
        if (cards.isEmpty()) {
            return false;
        }
        Card firstCard = cards.first();
        return firstCard.getNumber() != null && firstCard.getNumber() == CardValue.ACE;
    }
}
