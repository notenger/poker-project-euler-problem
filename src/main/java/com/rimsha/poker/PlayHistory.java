package com.rimsha.poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayHistory {

    private static List<Hand[]> plays = new ArrayList<>();

    public static void loadCombinations() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/p054_poker.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] cardsLine = line.split(" ");

                List<Card> cardsOfFirstPlayer = new ArrayList<>();
                List<Card> cardsOfSecondPlayer = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    Card card = new Card(
                        Rank.fromChar(cardsLine[i].charAt(0)),
                        Suit.fromChar(cardsLine[i].charAt(1))
                    );
                    if (i < 5) cardsOfFirstPlayer.add(card);
                    else cardsOfSecondPlayer.add(card);
                }
                Hand[] hands = {new Hand(cardsOfFirstPlayer), new Hand(cardsOfSecondPlayer)};
                plays.add(hands);

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<Hand[]> getPlays() {
        if (plays.isEmpty()) loadCombinations();
        return plays;
    }
}
