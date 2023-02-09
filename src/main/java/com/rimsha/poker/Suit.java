package com.rimsha.poker;

import java.util.Arrays;

public enum Suit {
    DIAMONDS, CLUBS,
    HEARTS, SPADES;

    public static Suit fromChar(char sign) {
        return Arrays.stream(values()).filter(s -> s.toString().charAt(0) == sign).findFirst().get();
    }
}
