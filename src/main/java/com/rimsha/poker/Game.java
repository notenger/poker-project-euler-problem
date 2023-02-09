package com.rimsha.poker;

import java.util.*;
import static com.rimsha.poker.Player.*;

public class Game {

    private Player firstPlayer;
    private Player secondPlayer;

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public Optional<Player> chooseWinner() {

        int handRankingsComparisonResult = firstPlayer.getHand().getHandRanking()
                .compare(firstPlayer.getHand().getHandRanking(), secondPlayer.getHand().getHandRanking());
        if (handRankingsComparisonResult > 0)
            return Optional.of(FIRST);
        if (handRankingsComparisonResult < 0)
            return Optional.of(SECOND);

        for (int i = 0; i < firstPlayer.getHand().getSortedRanks().size(); i++) {
            int ranksComparisonResult = firstPlayer.getHand().getSortedRanks().get(i)
                    .compare(firstPlayer.getHand().getSortedRanks().get(i), secondPlayer.getHand().getSortedRanks().get(i));
            if (ranksComparisonResult > 0)
                return Optional.of(FIRST);
            if (ranksComparisonResult < 0)
                return Optional.of(SECOND);
        }

        for (int i = 0; i < firstPlayer.getHand().getSortedCards().size(); i++) {
            int cardWiseComparisonResult = firstPlayer.getHand().getSortedCards().get(i)
                    .compareTo(secondPlayer.getHand().getSortedCards().get(i));
            if (cardWiseComparisonResult > 0) return Optional.of(FIRST);
            if (cardWiseComparisonResult < 0) return Optional.of(SECOND);
        }

        return Optional.empty();
    }
}
