package com.comparaonline.enums;

public enum Suit {
    HEARTS("hearts"), DIAMONDS("diamonds"), CLUBS("clubs"), SPADES("spades");

    private String display;

    Suit(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public static Suit fromDisplay(String display) {
        for (Suit suit : values()) {
            if (suit.getDisplay().equals(display)) {
                return suit;
            }
        }
        return null;
    }
}
