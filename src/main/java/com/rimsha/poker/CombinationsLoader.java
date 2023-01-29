package com.rimsha.poker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombinationsLoader {

    private static List<Hand[]> plays = new ArrayList<>();

    public static void loadCombinations() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/p054_poker.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] cardsLine = line.split(" ");

                Hand[] hands = {new Hand(), new Hand()};

                for (int i = 0; i < 10; i++) {
                    Card card = Card.createCard(cardsLine[i]);
                    if (i < 5) hands[0].addCard(card);
                    else hands[1].addCard(card);
                }

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
