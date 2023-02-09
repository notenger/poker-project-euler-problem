package com.rimsha.poker;
import java.util.Optional;

import static com.rimsha.poker.Player.*;

public class Poker {

    public static void main(String[] args) {

        int winsFirst = 0;
        int winsSecond = 0;

        for (Hand[] play : PlayHistory.getPlays()) {

            FIRST.setHand(play[0]);
            SECOND.setHand(play[1]);

            Game game = new Game(FIRST, SECOND);
            Optional<Player> winner = game.chooseWinner();
            if (winner.isPresent()) {
                if (winner.get() == FIRST) winsFirst++;
                if (winner.get() == SECOND) winsSecond++;
            }
            else {
                System.out.println("There is no clear winner.");
            }
        }

        System.out.println(
                String.format("The first player won %s times\nThe second player won %s times", winsFirst, winsSecond)
        );
    }

}
