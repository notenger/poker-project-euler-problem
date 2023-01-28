package com.rimsha.poker;

public enum Rank {
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

    public char toSign() {
        switch (this) {
            case DEUCE: return '2';
            case THREE: return '3';
            case FOUR: return '4';
            case FIVE: return '5';
            case SIX: return '6';
            case SEVEN: return '7';
            case EIGHT: return '8';
            case NINE: return '9';
            default: return toString().charAt(0);
        }
    }
}
