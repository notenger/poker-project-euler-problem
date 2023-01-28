package com.rimsha.poker;

import java.util.Arrays;
import java.util.Objects;
import java.util.SimpleTimeZone;

public class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card createCard(String cardSign) {

        Suit suit = Arrays.stream(Suit.values()).filter(s -> s.toString().charAt(0) == cardSign.charAt(1))
                .findFirst().get();
        Rank rank = Arrays.stream(Rank.values()).filter(r -> r.toSign() == cardSign.charAt(0))
                .findFirst().get();

        Card card = new Card(rank, suit);
        return card;
    }

    @Override
    public int compareTo(Card o) {
        return rank.getOrder() - o.getRank().getOrder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return rank == card.rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit);
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}
