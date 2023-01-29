package com.rimsha.poker;

import java.util.*;

public class Poker {

    public static void main(String[] args) {

        int winsFirst = 0;
        int winsSecond = 0;

        for (Hand[] play : CombinationsLoader.getPlays()) {
            Hand handOne = play[0];
            Hand handTwo = play[1];

            Game game = new Game(handOne, handTwo);
            Players winner = game.chooseWinner();
            if (winner == Players.FIRST) winsFirst++;
            if (winner == Players.SECOND) winsSecond++;
        }

        System.out.println(
                String.format("The first player won %s times\nThe second player won %s times", winsFirst, winsSecond)
        );

    }

}
