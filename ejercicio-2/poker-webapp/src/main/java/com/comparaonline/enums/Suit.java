package com.comparaonline.enums;

public enum Suit {
    HEARTS("hearts", 1), DIAMONDS("diamonds", 2), CLUBS("clubs", 3), SPADES("spades", 4);

    private String display;
    private int order;

    Suit(final String display, final int order) {
        this.display = display;
        this.order = order;
    }

    public String getDisplay() {
        return display;
    }

    public int getOrder() {
        return order;
    }

    public static Suit fromDisplay(final String display) {
        for (Suit suit : values()) {
            if (suit.getDisplay().equals(display)) {
                return suit;
            }
        }
        return null;
    }
}
