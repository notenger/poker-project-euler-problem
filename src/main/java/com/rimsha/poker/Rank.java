package com.rimsha.poker;

import java.util.Comparator;

public enum Rank implements Comparator<Rank> {
    DEUCE(0), THREE(1), FOUR(2),
    FIVE(3), SIX(4), SEVEN(5),
    EIGHT(6), NINE(7), TEN(8),
    JACK(9), QUEEN(10), KING(11), ACE(12);

    private int order;

    Rank(final int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public static Rank fromChar(char sign) {
        switch (sign) {
            case '2': return DEUCE;
            case '3': return THREE;
            case '4': return FOUR;
            case '5': return FIVE;
            case '6': return SIX;
            case '7': return SEVEN;
            case '8': return EIGHT;
            case '9': return NINE;
            case 'T': return TEN;
            case 'J': return JACK;
            case 'Q': return QUEEN;
            case 'K': return KING;
            default: return ACE;
        }
    }

    @Override
    public int compare(Rank r1, Rank r2) {
        return r1.getOrder() - r2.getOrder();
    }
}
