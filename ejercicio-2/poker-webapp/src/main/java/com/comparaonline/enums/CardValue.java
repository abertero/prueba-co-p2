package com.comparaonline.enums;

public enum CardValue {
    ACE(1, "A", 20), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K");

    private int number;
    private String display;
    private int value;

    CardValue(final int number, final String display, final int value) {
        this.number = number;
        this.display = display;
        this.value = value;
    }

    CardValue(final int number, final String display) {
        this.number = number;
        this.display = display;
        this.value = number;
    }

    CardValue(int number) {
        this.number = number;
        this.display = String.valueOf(number);
        this.value = number;
    }

    public int getNumber() {
        return number;
    }

    public String getDisplay() {
        return display;
    }

    public int getValue() {
        return value;
    }

    public CardValue getNext() {
        return fromNumber(number % 13 + 1);
    }

    public static CardValue fromDisplay(final String display) {
        for (CardValue value : values()) {
            if (value.getDisplay().equals(display)) {
                return value;
            }
        }
        return null;
    }

    public static CardValue fromNumber(final int number) {
        for (CardValue value : values()) {
            if (value.getNumber() == number) {
                return value;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        for (CardValue cardValue : CardValue.values()) {
            System.out.println(String.format("NEXT %s: %s", cardValue, cardValue.getNext()));
        }
    }
}
