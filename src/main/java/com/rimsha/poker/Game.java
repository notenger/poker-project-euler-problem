package com.rimsha.poker;

import java.util.*;
import java.util.stream.Collectors;

public class Game {

    private Hand handOne;
    private Hand handTwo;

    private HandRankings handRankingsOne;
    private HandRankings handRankingsTwo;
    private List<Rank> ranksOne;
    private List<Rank> ranksTwo;

    public Game(Hand handOne, Hand handTwo) {
        this.handOne = handOne;
        this.handTwo = handTwo;
        init();
    }

    private void init() {
        handRankingsOne = determineHandRanking(handOne).entrySet().iterator().next().getKey();
        handRankingsTwo = determineHandRanking(handTwo).entrySet().iterator().next().getKey();
        ranksOne = determineHandRanking(handOne).entrySet().iterator().next().getValue();
        ranksTwo = determineHandRanking(handTwo).entrySet().iterator().next().getValue();
    }

    public Players chooseWinner() {

        HandRankingsComparator handRankingsComparator = new HandRankingsComparator();
        if (handRankingsComparator.compare(handRankingsOne, handRankingsTwo) > 0) return Players.FIRST;
        if (handRankingsComparator.compare(handRankingsOne, handRankingsTwo) < 0) return Players.SECOND;

        List<Rank> ranksOneSorted = ranksOne.stream().sorted((r1, r2) -> Integer.compare(r2.getOrder(), r1.getOrder()))
                .collect(Collectors.toList());
        List<Rank> ranksTwoSorted = ranksTwo.stream().sorted((r1, r2) -> Integer.compare(r2.getOrder(), r1.getOrder()))
                .collect(Collectors.toList());

        RankComparator rankComparator = new RankComparator();
        for (int i = 0; i < ranksOneSorted.size(); i++) {
            if (rankComparator.compare(ranksOneSorted.get(i), ranksTwoSorted.get(i)) > 0) return Players.FIRST;
            if (rankComparator.compare(ranksOneSorted.get(i), ranksTwoSorted.get(i)) < 0) return Players.SECOND;
        }

        List<Card> cardsOneSorted = handOne.getCards().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Card> cardsTwoSorted = handTwo.getCards().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        for (int i = 0; i < cardsOneSorted.size(); i++) {
            if (cardsOneSorted.get(i).compareTo(cardsTwoSorted.get(i)) > 0) return Players.FIRST;
            if (cardsOneSorted.get(i).compareTo(cardsTwoSorted.get(i)) < 0) return Players.SECOND;
        }

        return Players.DRAW;
    }

    public static Map<HandRankings, List<Rank>> determineHandRanking(Hand hand) {

        Map<Rank, Long> frequencyMap = getFrequencyMap(hand);

        if (getMaxRank(hand) == Rank.ACE && areConsecutive(hand) && areSameSuit(hand)) {
            return Collections.singletonMap(HandRankings.ROYAL_FLUSH, List.of());
        }

        if (areConsecutive(hand) && areSameSuit(hand)) {
            return Collections.singletonMap(HandRankings.STRAIGHT_FLUSH, List.of());
        }

        if (frequencyMap.containsValue(4L)) {
            Rank rank = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(4L))
                    .map(Map.Entry::getKey).findFirst().get();
            return Collections.singletonMap(HandRankings.FOUR_OF_A_KIND, List.of(rank));
        }

        if (frequencyMap.values().containsAll(List.of(2L, 3L))) {
            Rank rank = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(3L))
                    .map(Map.Entry::getKey).findFirst().get();
            return Collections.singletonMap(HandRankings.FULL_HOUSE, List.of(rank));
        }

        if (areSameSuit(hand)) {
            return Collections.singletonMap(HandRankings.FLUSH, List.of());
        }

        if (areConsecutive(hand)) {
            return Collections.singletonMap(HandRankings.STRAIGHT, List.of());
        }

        if (frequencyMap.containsValue(3L)) {
            Rank rank = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(3L))
                    .map(Map.Entry::getKey).findFirst().get();
            return Collections.singletonMap(HandRankings.THREE_OF_A_KIND, List.of(rank));
        }

        if (Collections.frequency(frequencyMap.values() ,2L) == 2) {
            List<Rank> ranks = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(2L))
                    .map(Map.Entry::getKey).collect(Collectors.toList());
            return Collections.singletonMap(HandRankings.TWO_PAIRS, ranks);
        }

        if (frequencyMap.containsValue(2L)) {
            Rank rank = frequencyMap.entrySet().stream().filter(entry -> entry.getValue().equals(2L))
                    .map(Map.Entry::getKey).findFirst().get();
            return Collections.singletonMap(HandRankings.ONE_PAIR, List.of(rank));
        }

        return Collections.singletonMap(HandRankings.HIGH_CARD, List.of(getMaxRank(hand)));

    }

    public static boolean areConsecutive(Hand hand) {
        List<Card> cardsSorted = hand.getCards().stream().sorted().collect(Collectors.toList());

        for (int i = 1; i < cardsSorted.size(); i++) {
            if (cardsSorted.get(i).compareTo(cardsSorted.get(i-1)) != 1)
                return false;
        }

        return true;
    };
    public static boolean areSameSuit(Hand hand) {
        return hand.getCards().stream().map(Card::getSuit).distinct().count() <= 1;
    };
    public static Map<Rank, Long> getFrequencyMap(Hand hand) {
        return hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }
    public static Rank getMaxRank(Hand hand) {
        return Collections.max(hand.getCards()).getRank();
    }

}
