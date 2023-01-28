package com.rimsha.poker;

import java.util.Comparator;

public class RankComparator implements Comparator<Rank> {
    @Override
    public int compare(Rank r1, Rank r2) {
        return r1.getOrder() - r2.getOrder();
    }
}
