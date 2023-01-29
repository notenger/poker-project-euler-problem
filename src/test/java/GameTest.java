import com.rimsha.poker.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;

class GameTest {

    @Test
    void handRankingShouldBeFullHouseTens() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.FOUR, Suit.CLUBS));
        hand.addCard(new Card(Rank.TEN, Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN, Suit.CLUBS));
        hand.addCard(new Card(Rank.FOUR, Suit.DIAMONDS));
        hand.addCard(new Card(Rank.TEN, Suit.SPADES));

        Map<HandRankings, List<Rank>> expectedResult = Collections.singletonMap(HandRankings.FULL_HOUSE, List.of(Rank.TEN));
        assertEquals(expectedResult, Game.determineHandRanking(hand));
    }

    @Test
    void handRankingShouldBeTwoPairsKingsAndJacks() {
        Hand hand = new Hand();
        hand.addCard(new Card(Rank.KING, Suit.CLUBS));
        hand.addCard(new Card(Rank.DEUCE, Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK, Suit.CLUBS));
        hand.addCard(new Card(Rank.KING, Suit.DIAMONDS));
        hand.addCard(new Card(Rank.JACK, Suit.SPADES));

        Map<HandRankings, List<Rank>> expectedResult = Collections.singletonMap(HandRankings.TWO_PAIRS, List.of(Rank.KING, Rank.JACK));
        assertEquals(expectedResult, Game.determineHandRanking(hand));
    }

    @Test
    void secondShouldWin() {
        Hand handOne = new Hand();
        handOne.addCard(new Card(Rank.KING, Suit.CLUBS));
        handOne.addCard(new Card(Rank.DEUCE, Suit.HEARTS));
        handOne.addCard(new Card(Rank.JACK, Suit.CLUBS));
        handOne.addCard(new Card(Rank.KING, Suit.DIAMONDS));
        handOne.addCard(new Card(Rank.DEUCE, Suit.SPADES));

        Hand handTwo = new Hand();
        handTwo.addCard(new Card(Rank.SIX, Suit.SPADES));
        handTwo.addCard(new Card(Rank.SEVEN, Suit.SPADES));
        handTwo.addCard(new Card(Rank.THREE, Suit.SPADES));
        handTwo.addCard(new Card(Rank.FIVE, Suit.SPADES));
        handTwo.addCard(new Card(Rank.FOUR, Suit.SPADES));

        Game game = new Game(handOne, handTwo);
        assertEquals(Players.SECOND, game.chooseWinner());
    }

}