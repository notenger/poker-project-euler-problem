package com.rimsha.poker;

import java.util.Comparator;

public enum HandRanking implements Comparator<HandRanking> {

    HIGH_CARD(0),
    ONE_PAIR(1),
    TWO_PAIRS(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    FOUR_OF_A_KIND(7),
    STRAIGHT_FLUSH(8),
    ROYAL_FLUSH(9);

    private int order;

    HandRanking(final int order) {
        this.order = order;
    }

    public int getOrder() { return order; }

    @Override
    public int compare(HandRanking hr1, HandRanking hr2) {
        return hr1.getOrder() - hr2.getOrder();
    }
}
