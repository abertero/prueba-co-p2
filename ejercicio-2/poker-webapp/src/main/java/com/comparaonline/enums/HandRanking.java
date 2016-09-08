package com.comparaonline.enums;

import com.comparaonline.beans.Card;
import com.comparaonline.beans.Hand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum HandRanking {
    HIGH_CARD(1), ONE_PAIR(2), TWO_PAIRS(3), THREE_OF_A_KIND(4),
    STRAIGHT(5), FLUSH(6), FULL_HOUSE(7), FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(9), ROYAL_FLUSH(10);

    private int ranking;

    HandRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getRanking() {
        return ranking;
    }

    public static HandRanking ranksHand(Hand hand) {
        if (hand == null || hand.getCards().isEmpty()) {
            return null;
        }
        if (areSameSuitCards(hand)) {
            if (areConsecutiveCards(hand)) {
                if (isRoyalHand(hand)) {
                    return ROYAL_FLUSH;
                } else {
                    return STRAIGHT_FLUSH;
                }
            } else {
                // se retorna flush, porque si todos son de la misma pinta, no es posible que haya pares, trios o cuartas.
                return FLUSH;
            }
        } else {
            if (hasExactlyRepeatsInHand(hand, 4)) {
                return FOUR_OF_A_KIND;
            } else {
                boolean threeOfAKind = hasExactlyRepeatsInHand(hand, 3);
                boolean pair = hasExactlyRepeatsInHand(hand, 2);
                if (threeOfAKind && pair) {
                    return FULL_HOUSE;
                } else if (areConsecutiveCards(hand)) {
                    return STRAIGHT;
                } else if (threeOfAKind) {
                    return THREE_OF_A_KIND;
                } else if (pair && twoPairs(hand)) { // se hace este && para no tener que invocar a twoPairs en caso que no haya ninguno.
                    return TWO_PAIRS;
                } else if (pair) {
                    return ONE_PAIR;
                }
            }
        }
        return HIGH_CARD;
    }

    private static boolean twoPairs(Hand hand) {
        return !getTwoPairs(hand).isEmpty();
    }

    public static List<CardValue> getTwoPairs(Hand hand) {
        CardValue aux = null;
        CardValue firstPairValue = null;
        for (Card card : hand.getCards()) {
            if (card == null || card.getNumber() == null) {
                continue;
            } else if (aux == null) {
                aux = card.getNumber();
            } else if (aux == card.getNumber()) {
                if (firstPairValue == null) {
                    firstPairValue = aux;
                } else {
                    if (firstPairValue != aux) {
                        return Arrays.asList(firstPairValue, aux);
                    } else {
                        return new ArrayList<>();
                    }
                }
            } else {
                aux = card.getNumber();
            }
        }
        return new ArrayList<>();
    }

    /**
     * Revisa que haya exactamente expectedRepeats repeticiones de cualquier numero en hand.
     *
     * @param hand            la mano.
     * @param expectedRepeats la cantidad de repeticiones exacta que se espera.
     * @return true si es que hay la cantidad esperada exactamente.
     */
    private static boolean hasExactlyRepeatsInHand(Hand hand, int expectedRepeats) {
        return getExactlyRepeatsInHand(hand, expectedRepeats) != null;
    }

    public static CardValue getExactlyRepeatsInHand(Hand hand, int expectedRepeats) {
        int counter = 0;
        CardValue aux = null;
        for (Card card : hand.getCards()) {
            if (card == null || card.getNumber() == null) {
                if (counter == expectedRepeats) {
                    return aux;
                } else {
                    counter = 0;
                    aux = null;
                }
            } else if (aux == null) {
                ++counter;
                aux = card.getNumber();
            } else if (aux == card.getNumber()) {
                ++counter;
            } else {
                if (counter == expectedRepeats) {
                    return aux;
                } else {
                    counter = 1;
                    aux = card.getNumber();
                }
            }
        }
        return counter == expectedRepeats ? aux : null;
    }

    private static boolean areConsecutiveCards(Hand hand) {
        CardValue aux = null;
        // como hand.getCards() es un set ordenado, partimos del primer valor, comparando hacia arriba,
        // existe un caso especial que es cuando la escala debe continuar por el as. En ese caso la escala
        // parte por el as.
        for (Card card : hand.getCards()) {
            if (card == null || card.getNumber() == null) {
                return false;
            } else if (aux == null) {
                aux = card.getNumber().getNext();
            } else if (aux != card.getNumber()) {
                if (hand.hasAce()) {
                    aux = getConsecutiveInHandWithAce(aux);
                    if (aux != card.getNumber()) {
                        return false;
                    } else {
                        aux = card.getNumber().getNext();
                    }
                } else {
                    return false;
                }
            } else {
                aux = card.getNumber().getNext();
            }
        }
        return true;
    }

    /**
     * Se intenta adivinar el valor del siguiente en casos donde la mano contiene un as. Son los casos mas
     * raros para nuestra lista ordenada.
     *
     * @param aux El valor del siguiente actual.
     * @return El valor del siguiente que se quiere adivinar.
     */
    private static CardValue getConsecutiveInHandWithAce(CardValue aux) {
        switch (aux) {
            case TWO:   // caso A,10,J,Q,K
                return CardValue.TEN;
            case THREE: // caso A,2,J,Q,K
                return CardValue.JACK;
            case FOUR:  // caso A,2,3,Q,K
                return CardValue.QUEEN;
            case FIVE:  // caso A,2,3,4,K
                return CardValue.KING;
            default:
                return aux;
        }
    }

    private static boolean areSameSuitCards(Hand hand) {
        Suit aux = null;
        for (Card card : hand.getCards()) {
            if (card == null || card.getSuit() == null) {
                return false;
            } else if (aux == null) {
                aux = card.getSuit();
            } else if (aux != card.getSuit()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Revisa si la mano es una mano real, para esto basta comparar que sus valores sean mayores a 10.
     * En el punto donde se ejecuta esta funcion ya se comprobó que tienen la misma pinta y que
     * forman una escala.
     *
     * @param hand la mano.
     * @return true si forman A,10,J,Q,K
     */
    private static boolean isRoyalHand(Hand hand) {
        for (Card card : hand.getCards()) {
            if (card == null || card.getNumber() == null) {
                return false;
            } else if (card.getNumber().getValue() < 10) {
                return false;
            }
        }
        return true;
    }
}
