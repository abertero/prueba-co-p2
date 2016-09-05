package com.comparaonline.enums;

public enum CardValue {
    ACE(1, "A"), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(11, "J"), QUEEN(12, "Q"), KING(13, "K");

    private int number;
    private String display;

    CardValue(int number, String display) {
        this.number = number;
        this.display = display;
    }

    CardValue(int number) {
        this.number = number;
        this.display = String.valueOf(number);
    }

    public int getNumber() {
        return number;
    }

    public String getDisplay() {
        return display;
    }

    public static CardValue fromDisplay(String display) {
        for (CardValue value : values()) {
            if (value.getDisplay().equals(display)) {
                return value;
            }
        }
        return null;
    }
}
