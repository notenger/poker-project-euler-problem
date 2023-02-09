package com.rimsha.poker;

import java.util.*;
import java.util.stream.Collectors;

final public class Hand {

    private final List<Card> cards = new ArrayList<>();
    private final Map<HandRanking, List<Rank>> handRankingWithRanks;

    public Hand(final List<Card> cards) {
        this.cards.addAll(cards);
        handRankingWithRanks = determineHandRanking();
    }

    public List<Card> getCards() {
        return cards;
    }

    public HandRanking getHandRanking() {
        return handRankingWithRanks.entrySet().iterator().next().getKey();
    }

    public List<Rank> getRanks() {
        return handRankingWithRanks.entrySet().iterator().next().getValue();
    }

    public List<Rank> getSortedRanks() {
        return getRanks().stream().sorted((r1, r2) -> Integer.compare(r2.getOrder(), r1.getOrder()))
                .collect(Collectors.toList());
    }

    public List<Card> getSortedCards() {
        return cards.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    private Map<HandRanking, List<Rank>> determineHandRanking() {

        Map<Rank, Long> frequencyMap = getFrequencyMap();

        if (getMaxRank() == Rank.ACE && areConsecutive() && areSameSuit()) {
            return Collections.singletonMap(HandRanking.ROYAL_FLUSH, List.of());
        }

        if (areConsecutive() && areSameSuit()) {
            return Collections.singletonMap(HandRanking.STRAIGHT_FLUSH, List.of());
        }

        if (frequencyMap.containsValue(4L)) {
            Rank rank = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(4L))
                    .map(Map.Entry::getKey).findFirst().get();
            return Collections.singletonMap(HandRanking.FOUR_OF_A_KIND, List.of(rank));
        }

        if (frequencyMap.values().containsAll(List.of(2L, 3L))) {
            Rank rank = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(3L))
                    .map(Map.Entry::getKey).findFirst().get();
            return Collections.singletonMap(HandRanking.FULL_HOUSE, List.of(rank));
        }

        if (areSameSuit()) {
            return Collections.singletonMap(HandRanking.FLUSH, List.of());
        }

        if (areConsecutive()) {
            return Collections.singletonMap(HandRanking.STRAIGHT, List.of());
        }

        if (frequencyMap.containsValue(3L)) {
            Rank rank = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(3L))
                    .map(Map.Entry::getKey).findFirst().get();
            return Collections.singletonMap(HandRanking.THREE_OF_A_KIND, List.of(rank));
        }

        if (Collections.frequency(frequencyMap.values() ,2L) == 2) {
            List<Rank> ranks = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(2L))
                    .map(Map.Entry::getKey).collect(Collectors.toList());
            return Collections.singletonMap(HandRanking.TWO_PAIRS, ranks);
        }

        if (frequencyMap.containsValue(2L)) {
            Rank rank = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(2L))
                    .map(Map.Entry::getKey).findFirst().get();
            return Collections.singletonMap(HandRanking.ONE_PAIR, List.of(rank));
        }

        return Collections.singletonMap(HandRanking.HIGH_CARD, List.of(getMaxRank()));

    }

    private boolean areConsecutive() {
        List<Card> cardsSorted = cards.stream().sorted().collect(Collectors.toList());

        for (int i = 1; i < cardsSorted.size(); i++) {
            if (cardsSorted.get(i).compareTo(cardsSorted.get(i-1)) != 1)
                return false;
        }

        return true;
    };

    private boolean areSameSuit() {
        return cards.stream().map(Card::getSuit).distinct().count() <= 1;
    };

    private Map<Rank, Long> getFrequencyMap() {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }

    private Rank getMaxRank() {

        return Collections.max(cards).getRank();
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
