package com.rimsha.poker;

public enum Player {
    FIRST, SECOND;

    private Hand hand;

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }


}
