package com.rimsha.poker;

import java.util.Comparator;

public class HandRankingsComparator implements Comparator<HandRankings> {
    @Override
    public int compare(HandRankings hr1, HandRankings hr2) {
        return hr1.getOrder() - hr2.getOrder();
    }
}
